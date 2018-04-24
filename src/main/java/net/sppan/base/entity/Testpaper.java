package net.sppan.base.entity;

import com.alibaba.fastjson.annotation.JSONField;
import net.sppan.base.entity.support.BaseEntity;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * 考试类
 * Created by mutoulazy on 2018/4/23.
 */
@Entity
@Table(name = "tb_testpaper")
public class Testpaper extends BaseEntity{
    private static final long serialVersionUID = 1L;

    /**
     * 试卷id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    /**
     * 考试名称
     */
    private String name;

    /**
     * 是否隐藏
     *
     * 0显示 1隐藏
     */
    private Integer isHide;

    /**
     * 创建时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 更新时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    @ManyToMany(cascade = { CascadeType.REFRESH }, fetch = FetchType.LAZY)
    @JoinTable(name = "tb_testpaper_bank", joinColumns = { @JoinColumn(name = "paper_id") }, inverseJoinColumns = { @JoinColumn(name = "question_id") })
    private Set<QuestionBank> questionBanks;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getIsHide() {
        return isHide;
    }

    public void setIsHide(Integer isHide) {
        this.isHide = isHide;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Set<QuestionBank> getQuestionBanks() {
        return questionBanks;
    }

    public void setQuestionBanks(Set<QuestionBank> questionBanks) {
        this.questionBanks = questionBanks;
    }
}
