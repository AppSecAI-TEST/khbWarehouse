/**
 * @author 陈大涛
 * 2016-10-21下午4:13:39
 */
package com.yeepay.g3.facade.zt.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description
 * @author 陈大涛
 * 2016-10-21下午4:13:39
 */
public class ZtSceneDTO  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3608285756604257424L;

	private Long id;

    private Long version;
    /**场景名称 **/
    private String sceneName;
    /** 场景描述**/
    private String sceneDesc;
    /**场景类别 **/
    private String sceneType;
    /**创建时间**/
    private Date createTime;
    /**创建人 **/
    private String creator;
    /**审核状态 **/
    private String checkStatus;
    /** 审核时间**/
    private Date checkTime;
    /** 审核人	**/
    private String checker;
    /**场景图片 **/
    private byte[] sceneIcon;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public String getSceneName() {
        return sceneName;
    }

    public void setSceneName(String sceneName) {
        this.sceneName = sceneName == null ? null : sceneName.trim();
    }

    public String getSceneDesc() {
        return sceneDesc;
    }

    public void setSceneDesc(String sceneDesc) {
        this.sceneDesc = sceneDesc == null ? null : sceneDesc.trim();
    }

    public String getSceneType() {
        return sceneType;
    }

    public void setSceneType(String sceneType) {
        this.sceneType = sceneType == null ? null : sceneType.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public String getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(String checkStatus) {
        this.checkStatus = checkStatus == null ? null : checkStatus.trim();
    }

    public Date getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(Date checkTime) {
        this.checkTime = checkTime;
    }

    public String getChecker() {
        return checker;
    }

    public void setChecker(String checker) {
        this.checker = checker == null ? null : checker.trim();
    }

    public byte[] getSceneIcon() {
        return sceneIcon;
    }

    public void setSceneIcon(byte[] sceneIcon) {
        this.sceneIcon = sceneIcon;
    }

	@Override
	public String toString() {
		return "ZtSceneDTO [id=" + id + ", version=" + version + ", sceneName="
				+ sceneName + ", sceneDesc=" + sceneDesc + ", sceneType="
				+ sceneType + ", createTime=" + createTime + ", creator="
				+ creator + ", checkStatus=" + checkStatus + ", checkTime="
				+ checkTime + ", checker=" + checker + "]";
	}
    
}
