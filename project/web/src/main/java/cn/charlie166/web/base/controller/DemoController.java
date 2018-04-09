package cn.charlie166.web.base.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Maps;

import cn.charlie166.web.store.controller.BaseController;

@RequestMapping(value = "/demo")
@RestController
public class DemoController extends BaseController {

	@RequestMapping(value = "/tree")
	public Object tree(){
		Map<String, Object> root = Maps.newHashMap();
		root.put("name", "总部");
		root.put("open", Boolean.TRUE);
		Map<String, Object> c1 = Maps.newHashMap();
		c1.put("name", "学院1");
		c1.put("open", Boolean.TRUE);
		Map<String, Object> c1_1 = Maps.newHashMap();
		c1_1.put("name", "班级1_1");
		Map<String, Object> c1_1_1 = Maps.newHashMap();
		c1_1_1.put("name", "小组1_1_1");
		c1_1.put("children", Arrays.asList(c1_1_1));
		Map<String, Object> c1_2 = Maps.newHashMap();
		c1_2.put("name", "班级1_2");
		c1_2.put("checked", Boolean.TRUE);
		c1.put("children", Arrays.asList(c1_1, c1_2));
		Map<String, Object> c2 = Maps.newHashMap();
		c2.put("name", "学院2");
		Map<String, Object> c2_1 = Maps.newHashMap();
		c2_1.put("name", "班级2_1");
		Map<String, Object> c2_2 = Maps.newHashMap();
		c2_2.put("name", "班级2_2");
		Map<String, Object> c2_3 = Maps.newHashMap();
		c2_3.put("name", "班级2_3");
		c2.put("children", Arrays.asList(c2_1, c2_2, c2_3));
		root.put("children", Arrays.asList(c1, c2));
		return root;
	}
}