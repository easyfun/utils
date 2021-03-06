package com.efun.framework.common.dto.base;

import com.efun.framework.common.enums.UserAgent;
import com.efun.framework.common.exception.BusinessException;
import com.efun.framework.common.exception.CommonErrorCode;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

public class BaseParamDTO implements Serializable {
	private static final long serialVersionUID = 8399649882058356349L;

	private String applyId;

	/**
	 * 客户端代理
	 */
	private UserAgent userAgent;

	/**
	 * 版本号
	 */
	private String version;

	/**
	 * 语言
	 */
	private String lang;

	/**
	 * 用户ip
	 */
	private String userIp;

	/**
	 * 用户区域
	 */
	private String userRegion;

	/**
	 * 用户设备id
	 */
	private String userDeviceId;

	/**
	 * 服务名字
	 */
	private String serviceName;

	/**
	 * 服务id
	 */
	private String serviceId;

	/**
	 * 服务ip
	 */
	private String serviceIp;

	public String getApplyId() {
		return applyId;
	}

	public void setApplyId(String applyId) {
		this.applyId = applyId;
	}

	public UserAgent getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(UserAgent userAgent) {
		this.userAgent = userAgent;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public String getUserIp() {
		return userIp;
	}

	public void setUserIp(String userIp) {
		this.userIp = userIp;
	}

	public String getUserRegion() {
		return userRegion;
	}

	public void setUserRegion(String userRegion) {
		this.userRegion = userRegion;
	}

	public String getUserDeviceId() {
		return userDeviceId;
	}

	public void setUserDeviceId(String userDeviceId) {
		this.userDeviceId = userDeviceId;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public String getServiceIp() {
		return serviceIp;
	}

	public void setServiceIp(String serviceIp) {
		this.serviceIp = serviceIp;
	}

	public void validateBaseParam() {
		validateApplyId();
		validateUserAgent();
		validateVersion();
		validateLang();
		validateUserIp();
//		validateUserRegion();
		validateUserDeviceId();
//		validateServiceId();
//		validateServiceIp();
	}

	public void validateApplyId() {
		if (StringUtils.isEmpty(applyId)) {
			throw new BusinessException(CommonErrorCode.requestParamError);
		}
	}

	public void validateUserAgent() {
		if (null == userAgent) {
			throw new BusinessException(CommonErrorCode.requestParamError);
		}
	}

	public void validateVersion() {
		if (StringUtils.isEmpty(version)) {
			throw new BusinessException(CommonErrorCode.requestParamError);
		}
	}

	public void validateLang() {
		if (StringUtils.isEmpty(lang)) {
			throw new BusinessException(CommonErrorCode.requestParamError);
		}
	}

	public void validateUserIp() {
		if (StringUtils.isEmpty(userIp)) {
			throw new BusinessException(CommonErrorCode.requestParamError);
		}
	}

	public void validateUserRegion() {
		if (StringUtils.isEmpty(userRegion)) {
			throw new BusinessException(CommonErrorCode.requestParamError);
		}
	}

	public void validateUserDeviceId() {
		if (StringUtils.isEmpty(userDeviceId)) {
			throw new BusinessException(CommonErrorCode.requestParamError);
		}
	}

	public void validateServiceId() {
		if (StringUtils.isEmpty(serviceId)) {
			throw new BusinessException(CommonErrorCode.requestParamError);
		}
	}

	public void validateServiceIp() {
		if (StringUtils.isEmpty(serviceIp)) {
			throw new BusinessException(CommonErrorCode.requestParamError);
		}
	}

}
