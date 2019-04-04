package com.swj.customized.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.swj.customized.bean.Image;
import com.swj.customized.bean.Product;
import com.swj.customized.mapper.ClassifyMapper;
import com.swj.customized.mapper.ImageMapper;
import com.swj.customized.mapper.MessageMapper;
import com.swj.customized.mapper.ProductMapper;
import com.swj.customized.tool.JSONTool;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by xxb on 2018/10/22.
 */
@RestController
@ResponseBody
@Api(value = "产品接口", description = "产品接口")
@RequestMapping("productManage")
@Slf4j
public class ProductManageController {


    @Resource
    private ProductMapper productMapper;
    @RequestMapping(value = "getProductID", method = RequestMethod.GET)
    @ApiOperation(value = "获取产品ID", notes = "获取产品ID")
    @Transactional
    public JSONObject getProductID() {
        JSONObject re = new JSONObject();
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        re.put("code", "1");
        re.put("message", "获取产品ID成功");
        re.put("result", uuid);
        return re;
    }



    @RequestMapping(value = "addProduct", method = RequestMethod.POST)
    @ApiOperation(value = "添加产品", notes = "添加新产品")
    @Transactional
    public JSONObject addProduct(@RequestBody Product product) {
        JSONObject re = new JSONObject();
        try {
            Product product1 = new Product();
            product1.setProductname(product.getProductname());
            product1.setClassid(product.getClassid());
            List<Product> products = productMapper.selectBySelective(product1);
            if (products.size() > 0) {
                re.put("code", "0");
                re.put("message", "添加产品失败，产品已存在");
            } else {
                product.setProductscore(0);
                product.setProductscorenum(0);
                product.setCreatetime(DateTime.now().toString("yyyyMMddHHmmss"));
                productMapper.insertSelective(product);
                re.put("code", "1");
                re.put("message", "添加产品成功");
                re.put("result", JSONTool.ObjectToJSONObject(product));
            }
        } catch (Exception e) {
            re.put("code", "0");
            re.put("message", "添加产品失败");
            re.put("Exception", e);
        }
        return re;
    }

    @RequestMapping(value = "JudgingWhetherUsersScoreOrNot", method = RequestMethod.GET)
    @ApiOperation(value = "判断用户是否评分", notes = "判断用户是否评分")
    public JSONObject JudgingWhetherUsersScoreOrNot(@RequestParam("id") String id,@RequestParam("userid") String userid) {
        JSONObject re = new JSONObject();
        try {
            int num=productMapper.selectishavauserByscore(id,userid);
            re.put("code", "1");
            re.put("message", "获取成功");
            if(num!=0){
                re.put("ishave",true);
            }else {
                re.put("ishave",false);
            }
        } catch (Exception e) {

            re.put("code", "0");
            re.put("message", "获取失败");
            re.put("Exception", e);
        }
        return re;
    }

    @RequestMapping(value = "updataProductscore", method = RequestMethod.GET)
    @ApiOperation(value = "修改产品评分", notes = "修改产品")
    public JSONObject updataProductscore(@RequestParam("id") String id, @RequestParam("score") double score, @RequestParam("userid") String userid) {
        JSONObject re = new JSONObject();
        try {
            Product product = productMapper.selectByPrimaryKey(id);
            double avescore = product.getProductscore();
            int scorenum = product.getProductscorenum();
            double sumscore = avescore * scorenum;
            scorenum += 1;
            sumscore = sumscore + score;
            double newavescore = sumscore / scorenum;
            Product product1 = new Product();
            product1.setId(id);
            product1.setProductscorenum(scorenum);
            product1.setProductscore(newavescore);
            productMapper.addProductscoreByUser(id, userid, score);
            productMapper.updateByPrimaryKeySelective(product1);
            re.put("code", "1");
            re.put("message", "产品评分成功");
        } catch (Exception e) {

            re.put("code", "0");
            re.put("message", "产品评分失败");
            re.put("Exception", e);
        }
        return re;
    }

    @RequestMapping(value = "updaProduct", method = RequestMethod.PUT)
    @Transactional
    @ApiOperation(value = "修改产品", notes = "修改产品")
    public JSONObject updataProduct(@RequestBody Product product) {
        JSONObject re = new JSONObject();
        try {
            if (product.getId() == null) {
                re.put("code", "0");
                re.put("message", "缺少id");
                return re;
            }
            product.setClassid(null);
            if (product.getProductname() != null) {
                Product product1 = new Product();
                product1.setProductname(product.getProductname());
                product1.setClassid(product.getClassid());
                List<Product> products = productMapper.selectBySelective(product1);
                if (products.size() > 0) {
                    for (Product product2 : products) {
                        if (product2.getId() != product.getId()) {
                            re.put("code", "0");
                            re.put("message", "修改产品失败，产品已存在");
                            return re;
                        }
                    }
                }
            }
            productMapper.updateByPrimaryKeySelective(product);
            re.put("code", "1");
            re.put("message", "修改产品成功");
        } catch (Exception e) {

            re.put("code", "0");
            re.put("message", "修改产品失败");
            re.put("Exception", e);
        }
        return re;
    }

    @RequestMapping(value = "deleteProduct", method = RequestMethod.DELETE)
    @ApiOperation(value = "删除产品", notes = "根据产品id删除产品")
    @Transactional
    public JSONObject deleteProduct(@RequestBody JSONArray productid) {
        JSONObject re = new JSONObject();
        try {
            List<String> ids = new ArrayList<>();
            for (int i = 0; i < productid.size(); i++) {
                ids.add(productid.getString(i));
            }
            productMapper.deleteByPrimaryKey(ids);
            re.put("code", "1");
            re.put("message", "删除产品成功");
        } catch (Exception e) {
            re.put("code", "0");
            re.put("message", "删除产品失败,请联系后台");
            re.put("Exception", e);
        }
        return re;
    }

    @RequestMapping(value = "getProductById", method = RequestMethod.GET)
    @ApiOperation(value = "获取指定产品基本信息", notes = "根据产品ID获取指定产品基本信息")
    public JSONObject getProductById(@RequestParam String productid) {
        JSONObject re = new JSONObject();
        Product c = productMapper.selectByPrimaryKey(productid);
        if (c == null) {
            re.put("code", "0");
            re.put("message", "产品获取失败或产品为空！");
            re.put("result", new JSONObject());
        } else {
            re.put("code", "1");
            re.put("message", "产品信息查询成功！");
            re.put("result", c);
        }
        return re;
    }

    @ApiOperation(value = "获取最新产品", notes = "获取最新的6个产品")
    @RequestMapping(value = "getNewProduct", method = RequestMethod.GET)
    public JSONObject getNewProduct() {
        JSONObject k = new JSONObject();
        try {
            List<Product> cs = productMapper.selectBySixNew();
            k.put("code", 1);
            k.put("message", "获取最新产品成功");
            k.put("result", cs);
        } catch (Exception e) {
            k.put("code", 0);
            k.put("message", "获取最新产品失败");
            k.put("result", new JSONObject());
            k.put("Exception", e);
        }
        return k;
    }


    @ApiOperation(value = "获取指定产品", notes = "获取指定产品")
    @RequestMapping(value = "getmeroProduct", method = RequestMethod.POST)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ispage", value = "是否使用分页", required = true, dataType = "boolean", paramType = "query"),
            @ApiImplicitParam(name = "pageNum", value = "查询页数", required = false, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "每页条数", required = false, dataType = "int", paramType = "query"),
    })
    public JSONObject getmeroProduct(@RequestParam(name = "ispage") boolean ispage,
                                     @RequestParam(name = "pageNum", required = false) Integer pageNum,
                                     @RequestParam(name = "pageSize", required = false) Integer pageSize,
                                     @RequestBody Product product) {
        if (ispage) {
            PageHelper.startPage(pageNum, pageSize);
        }
        JSONObject k = new JSONObject();

        try {
            List<Product> cs = productMapper.selectBySelective(product);
            k.put("code", 1);
            k.put("message", "查询成功");
            k.put("result", cs);
            if (ispage) {
                PageInfo<Product> pageInfo = new PageInfo<Product>(cs);
                k.put("result", pageInfo);
            }
        } catch (Exception e) {
            k.put("code", 0);
            k.put("message", "查询失败");
            k.put("result", new JSONObject());
            k.put("Exception", e);
        }
        return k;
    }

}
