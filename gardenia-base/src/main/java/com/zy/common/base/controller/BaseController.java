package com.zy.common.base.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zy.common.constant.CommonConstant;
import com.zy.common.utils.Result;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.util.Date;

/**
 * @Description: Controller基类
 * @Author: Gardenia ZY
 * @Date: 2019-9-19 9:13
 * @Version: 1.0
 */
@Slf4j
public class BaseController<T, S extends IService<T>> {

    @Autowired
    S service;

//    @Value("${gardenia.path.upload}")
//    private String upLoadPath;

    /**
     * 将前台传递过来的日期格式的字符串，自动转化为Date类型
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        // Date 类型转换
        binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                if (StrUtil.isNotEmpty(text)) {
                    // text 不为空，则将其转换为 Date
                    try {
                        setValue(DateUtils.parseDate(text));
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    // 为空即将设置为null
                    setValue(null);
                }
            }
        });
    }

    /**
     * 导出excel
     *
     * @param request
     */
    protected ModelAndView exportXls(HttpServletRequest request, T object, Class<T> clazz, String title) {
//        // Step.1 组装查询条件
//        QueryWrapper<T> queryWrapper = QueryGenerator.initQueryWrapper(object, request.getParameterMap());
//
//        // Step.2 获取导出数据
//        List<T> pageList = service.list(queryWrapper);
//        List<T> exportList = null;
//
//        // 过滤选中数据
//        String selections = request.getParameter("selections");
//        if (ConvertUtils.isNotEmpty(selections)) {
//            List<String> selectionList = Arrays.asList(selections.split(","));
//            exportList = pageList.stream().filter(item -> selectionList.contains(getId(item))).collect(Collectors.toList());
//        } else {
//            exportList = pageList;
//        }
//
//        // Step.3 AutoPoi 导出Excel
//        return this.exportXls(exportList,clazz,title);
        return null;
    }

    /**
     * 根据每页sheet数量导出多sheet
     *
     * @param request
     * @param object       实体类
     * @param clazz        实体类class
     * @param title        标题
     * @param exportFields 导出字段自定义
     * @param pageNum      每个sheet的数据条数
     * @param request
     */
    protected ModelAndView exportXlsSheet(HttpServletRequest request, T object, Class<T> clazz, String title, String exportFields, Integer pageNum) {
//        // Step.1 组装查询条件
//        QueryWrapper<T> queryWrapper = QueryGenerator.initQueryWrapper(object, request.getParameterMap());
//        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
//        // Step.2 计算分页sheet数据
//        double total = service.count();
//        int count = (int)Math.ceil(total/pageNum);
//        // Step.3 多sheet处理
//        List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
//        for (int i = 1; i <=count ; i++) {
//            Page<T> page = new Page<T>(i, pageNum);
//            IPage<T> pageList = service.page(page, queryWrapper);
//            List<T> records = pageList.getRecords();
//            List<T> exportList = null;
//            // 过滤选中数据
//            String selections = request.getParameter("selections");
//            if (oConvertUtils.isNotEmpty(selections)) {
//                List<String> selectionList = Arrays.asList(selections.split(","));
//                exportList = records.stream().filter(item -> selectionList.contains(getId(item))).collect(Collectors.toList());
//            } else {
//                exportList = records;
//            }
//            Map<String, Object> map = new HashMap<String, Object>();
//            ExportParams  exportParams=new ExportParams(title + "报表", "导出人:" + sysUser.getRealname(), title+i,upLoadPath);
//            exportParams.setType(ExcelType.XSSF);
//            //map.put("title",exportParams);//表格Title
//            map.put(NormalExcelConstants.PARAMS,exportParams);//表格Title
//            map.put(NormalExcelConstants.CLASS,clazz);//表格对应实体
//            map.put(NormalExcelConstants.DATA_LIST, exportList);//数据集合
//            listMap.add(map);
//        }
//        // Step.4 AutoPoi 导出Excel
//        ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
//        mv.addObject(NormalExcelConstants.FILE_NAME, title); //此处设置的filename无效 ,前端会重更新设置一下
//        mv.addObject(NormalExcelConstants.MAP_LIST, listMap);
//        return mv;
        return null;
    }

    /**
     * 根据权限导出excel，传入导出字段参数
     *
     * @param request
     */
    protected ModelAndView exportXls(HttpServletRequest request, T object, Class<T> clazz, String title, String exportFields) {
//        ModelAndView mv = this.exportXls(request,object,clazz,title);
//        mv.addObject(NormalExcelConstants.EXPORT_FIELDS,exportFields);
//        return mv;
        return null;
    }

    /**
     * 获取对象ID
     *
     * @return
     */
    private String getId(T item) {
        try {
            return PropertyUtils.getProperty(item, "id").toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 通过excel导入数据
     *
     * @param request
     * @param response
     * @return
     */
    protected Result<?> importExcel(HttpServletRequest request, HttpServletResponse response, Class<T> clazz) {
//        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
//        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
//        for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
//            MultipartFile file = entity.getValue();// 获取上传文件对象
//            ImportParams params = new ImportParams();
//            params.setTitleRows(2);
//            params.setHeadRows(1);
//            params.setNeedSave(true);
//            try {
//                List<T> list = ExcelImportUtil.importExcel(file.getInputStream(), clazz, params);
//                //update-begin-author:taoyan date:20190528 for:批量插入数据
//                long start = System.currentTimeMillis();
//                service.saveBatch(list);
//                //400条 saveBatch消耗时间1592毫秒  循环插入消耗时间1947毫秒
//                //1200条  saveBatch消耗时间3687毫秒 循环插入消耗时间5212毫秒
//                log.info("消耗时间" + (System.currentTimeMillis() - start) + "毫秒");
//                //update-end-author:taoyan date:20190528 for:批量插入数据
//                return Result.ok("文件导入成功！数据行数：" + list.size());
//            } catch (Exception e) {
//                log.error(e.getMessage(), e);
//                return Result.error("文件导入失败:" + e.getMessage());
//            } finally {
//                try {
//                    file.getInputStream().close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        return Result.error("文件导入失败！");
//    }
//
//    private LoginUser getLoginUser() {
//        LoginUser sysUser = null;
//        try {
//            sysUser = SecurityUtils.getSubject().getPrincipal() != null ? (LoginUser) SecurityUtils.getSubject().getPrincipal() : null;
//        } catch (Exception e) {
//            //e.printStackTrace();
//            sysUser = null;
//        }
//        return sysUser;
//    }
//
//    public String getUid(){
//        if (StringUtils.isNotBlank(getBossId()))
//            return getBossId();
//        LoginUser loginUser = getLoginUser();
//        if (loginUser==null)
//            throw new JeecgBootException("用户未找到");
//        return loginUser.getId();
        return null;
    }

    public String getLoginPlatform() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return attributes.getRequest().getHeader(CommonConstant.X_ACCESS_PLATFORM);
    }

}
