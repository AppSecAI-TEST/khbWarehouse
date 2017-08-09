package com.xinnet.view;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.servlet.view.freemarker.FreeMarkerView;



/**
 * 轻量级的FreeemarkerView
 * 
 * 不支持jsp标签、不支持request、session、application等对象
 */
public class MyFreeMarkerView extends FreeMarkerView {

    private static final String CONTEXT_PATH = "ctx";

   @Override
   protected void exposeHelpers(Map<String, Object> model, HttpServletRequest request) throws Exception {
       model.put(CONTEXT_PATH, request.getContextPath());
       super.exposeHelpers(model, request);
   }
}