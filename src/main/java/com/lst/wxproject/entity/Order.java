package com.lst.wxproject.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.List;

@Data
@TableName("orderList")
public class Order {
    @TableId(type = IdType.AUTO)
    private Integer id;                 //订单id
    private Integer productId;          //产品id
    private Integer refractivity;       //折射率 1:1.50 2:1.56 3:1.60 4:1.67 5:1.74
    private Integer film;               //镜片膜类型  1:睛锐膜 2:无影膜 3:焕享2代变色(睛锐膜) 4.防眩光膜  5.焕享2代变色(防眩光膜)
    private Integer blueRay;            //蓝光 1:是  0：否
    private Integer stain;              //染色类别 1.全染 2.半染 3.来样染色 0：不染
    private Integer thinLens;           //镜片美薄 0.不美薄 1.单焦点镜片 2.多焦点镜片
    private Integer thickening;         //中心加厚 0.不加厚 1.加厚
    private Integer markers;            //个人隐形标记  0.不添加 1.添加
    private String remarks;             //备注
    private Double lSphere;             //左球镜*
    private Double rSphere;             //右球镜*
    private Double lCylinder;           //左柱镜*
    private Double rCylinder;           //右柱镜*
    private Double lAxial;              //左轴位
    private Double rAxial;              //右轴位
    private String frameModels;         //框型
    private Double pupilDistance;       //瞳距
    private Double pupilHeight;         //瞳高
    private Double mirrorWidth;         //镜宽
    private Double beamWidth;           //梁宽
    private Double mirrorHeight;        //镜高
    private Double maxDiagonalLength;   //最大对角长
    private Double orderPic;            //订单照片*
    private Integer processType;        //加工方式 1.工厂加工 2.自加工
    private String createTime;          //创建时间
    private Integer uid;             //用户ID
    private Integer isActive;           //假删
    private String orderNum;            //订单号

    @TableField(exist = false)
    private List<User> Users;
}
