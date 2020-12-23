package com.bdjbd.dao.entity;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;


/**
 * 专家管理
 * @author 83746
 */
@Data
@Table(name = "qa_review_expert")
public class QaReviewExpertEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@Id
	private Integer id;
	/**
	 * 专家名称
	 */
	private String expertName;
	/**
	 * 账号
	 */
	private String accountNumber;

	private String password;
	/**
	 * 单位
	 */
	private String company;

}
