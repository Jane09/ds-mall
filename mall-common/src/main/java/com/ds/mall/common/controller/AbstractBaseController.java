package com.ds.mall.common.controller;

import com.ds.mall.common.context.DsMallContext;
import com.ds.mall.common.response.ObjectResponse;
import com.ds.mall.common.response.TableResponse;
import com.ds.mall.common.service.AbstractBaseService;
import com.ds.mall.common.service.Query;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author tb
 * @date 2019/1/7 14:17
 */
@Slf4j
@SuppressWarnings("unchecked")
public class AbstractBaseController<BaseSevice extends AbstractBaseService, Entity> {

    @Autowired
    protected HttpServletRequest request;

    @Autowired
    protected BaseSevice baseSevice;


    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public ObjectResponse<Entity> add(@RequestBody Entity entity) {
        baseSevice.insertSelective(entity);
        return new ObjectResponse<Entity>();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ObjectResponse<Entity> get(@PathVariable int id) {
        ObjectResponse<Entity> entityObjectRestResponse = new ObjectResponse<>();
        Object o = baseSevice.selectById(id);
        entityObjectRestResponse.data((Entity) o);
        return entityObjectRestResponse;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public ObjectResponse<Entity> update(@RequestBody Entity entity) {
        baseSevice.updateSelectiveById(entity);
        return new ObjectResponse<Entity>();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ObjectResponse<Entity> remove(@PathVariable int id) {
        baseSevice.deleteById(id);
        return new ObjectResponse<Entity>();
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    public List<Entity> all() {
        return baseSevice.selectListAll();
    }

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    @ResponseBody
    public TableResponse<Entity> list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        return baseSevice.selectByQuery(query);
    }

    public String getClientName() {
        return DsMallContext.getClientName();
    }
}
