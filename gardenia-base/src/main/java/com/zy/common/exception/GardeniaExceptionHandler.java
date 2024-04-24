package com.zy.common.exception;

import com.zy.common.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import java.util.stream.Collectors;


/**
 * 异常处理器
 *
 * @Author Gardenia ZY
 * @Date 2019
 */
@RestControllerAdvice
@Slf4j
public class GardeniaExceptionHandler {
	/**
	 * 处理自定义异常
	 */
	@ExceptionHandler(GardeniaException.class)
	public Result<?> handleRRException(GardeniaException e){
		log.error(e.getMessage(), e);
		return Result.error(e.getMessage());
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Result<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		BindingResult bindingResult = e.getBindingResult();
		String messages = bindingResult.getAllErrors()
				.stream()
				.map(ObjectError::getDefaultMessage)
				.collect(Collectors.joining("；"));
		return Result.error(500,messages);
	}

//	@ExceptionHandler(DuplicateKeyException.class)
//	public Result<?> handleDuplicateKeyException(DuplicateKeyException e){
//		log.error(e.getMessage(), e);
//		return Result.error("数据库中已存在该记录");
//	}

	@ExceptionHandler(Exception.class)
	public Result<?> handleException(Exception e){
		log.error(e.getMessage(), e);
		return Result.error("操作失败，"+e.getMessage());
	}

	/**
	 * @Author Gardenia ZY
	 * @param e
	 * @return
	 */
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public Result<?> HttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e){
		StringBuffer sb = new StringBuffer();
		sb.append("不支持");
		sb.append(e.getMethod());
		sb.append("请求方法，");
		sb.append("支持以下");
		String [] methods = e.getSupportedMethods();
		if(methods!=null){
			for(String str:methods){
				sb.append(str);
				sb.append("、");
			}
		}
		log.error(sb.toString(), e);
		//return Result.error("没有权限，请联系管理员授权");
		return Result.error(405,sb.toString());
	}

	 /**
	  * spring默认上传大小100MB 超出大小捕获异常MaxUploadSizeExceededException
	  */
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public Result<?> handleMaxUploadSizeExceededException(MaxUploadSizeExceededException e) {
    	log.error(e.getMessage(), e);
        return Result.error("文件大小超出10MB限制, 请压缩或降低文件质量! ");
    }

//    @ExceptionHandler(DataIntegrityViolationException.class)
//    public Result<?> handleDataIntegrityViolationException(DataIntegrityViolationException e) {
//    	log.error(e.getMessage(), e);
//        return Result.error("字段太长,超出数据库字段的长度");
//    }

}
