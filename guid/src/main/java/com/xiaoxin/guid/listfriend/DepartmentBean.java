package com.xiaoxin.guid.listfriend;

import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

/**
 * @author wfx
 * @date 2019/5/28 16:38
 * @desc
 */
public class DepartmentBean implements MultiItemEntity {

    public KcHrDeptBean kcHrDept;
    public List<KcHrEmployesBean> kcHrEmployes;

    @Override
    public int getItemType() {
        return 0;
    }

    public static class KcHrDeptBean extends AbstractExpandableItem<KcHrEmployesBean> implements MultiItemEntity{
        /**
         * ancestors : 0,100
         * createBy : admin
         * createTime : 1521171180000
         * delFlag : 0
         * deptId : 101
         * deptName : 院领导
         * email : jinju@jinjuyun.com
         * leader : 金桔云
         * orderNum : 1
         * parentId : 100
         * phone : 18953195702
         * status : 0
         * updateBy : admin
         * updateTime : 1558194848000
         */

        public String ancestors;
        public String createBy;
        public long createTime;
        public String delFlag;
        public int deptId;
        public String deptName;
        public String email;
        public String leader;
        public int orderNum;
        public int parentId;
        public String phone;
        public String status;
        public String updateBy;
        public long updateTime;
        public int number;
        public boolean select;
        public boolean isSelect() {
            return select;
        }

        public void setSelect(boolean select) {
            this.select = select;
        }

        @Override
        public int getItemType() {
            return DepartAdapter.TYPE_LEVEL_0;
        }

        @Override
        public int getLevel() {
            return 0;
        }
    }

    public static class KcHrEmployesBean implements MultiItemEntity{
            /**
             * createBy : cb57b96a14584125a224771d18399b19
             * createTime : 1558405710000
             * delFlag : 0
             * departureTime : 1684598400000
             * deptId : 101
             * employeHomeAdr :
             * employeId : 0f10a3ee87194ad48448af27e23dc0b4
             * employeMarry : 0
             * employeName : 魏检察长
             * employeSex : 0
             * employeWage : 0
             * entryTime : 1558281600000
             * firstLetter : W
             * generalHolidays : 30
             * headImage : /user/avatar//b14cbaa4854e4a25bf7003f5ce55dd17.png
             * isCheck : 0
             * isSign : 0
             * loginName : fjcz
             * loginPwd : fa2d9dd68114f86ff076665e2d3d5612
             * phoneCost : 0
             * postId : 2
             * pushType : 0
             * remark :
             * updateBy : cb57b96a14584125a224771d18399b19
             * updateTime : 1558405710000
             * workYear : 20
             * employeAdr :
             * employeAge :
             * employeBank :
             * employeBankCard :
             * employeCode : FY0001
             * employeEducation : 研究生
             * employeEmail :
             * employeHealth :
             * employeHeight :
             * employeIdNumber :
             * employeLangageUse :
             * employeMac :
             * employeNation :
             * employeNativePlace :
             * employePhone : 15959638999
             * employePolitical : 党员
             * employeProfessional :
             * employeQq :
             * employeSchool :
             * employeWechat :
             * employeWeight :
             * firstAdr :
             * firstName :
             * firstPhone :
             * fixedTel :
             * fixedTelShort : 6006
             * mnemonicCode :
             * pushId :
             * secondAdr :
             * secondName :
             * secondPhone :
             * threeAdr :
             * threeName :
             * threePhone :
             * verificationCode :
             */

            public String createBy;
            public long createTime;
            public String delFlag;
            public long departureTime;
            public String deptId;
            public String employeHomeAdr;
            public String employeId;
            public String employeMarry;
            public String employeName;
            public String employeSex;
            public int employeWage;
            public long entryTime;
            public String firstLetter;
            public int generalHolidays;
            public String headImage;
            public String isCheck;
            public String isSign;
            public String loginName;
            public String loginPwd;
            public int phoneCost;
            public String postId;
            public String pushType;
            public String remark;
            public String updateBy;
            public long updateTime;
            public int workYear;
            public String employeAdr;
            public String employeAge;
            public String employeBank;
            public String employeBankCard;
            public String employeCode;
            public String employeEducation;
            public String employeEmail;
            public String employeHealth;
            public String employeHeight;
            public String employeIdNumber;
            public String employeLangageUse;
            public String employeMac;
            public String employeNation;
            public String employeNativePlace;
            public String employePhone;
            public String employePolitical;
            public String employeProfessional;
            public String employeQq;
            public String employeSchool;
            public String employeWechat;
            public String employeWeight;
            public String firstAdr;
            public String firstName;
            public String firstPhone;
            public String fixedTel;
            public String fixedTelShort;
            public String mnemonicCode;
            public String pushId;
            public String secondAdr;
            public String secondName;
            public String secondPhone;
            public String threeAdr;
            public String threeName;
            public String threePhone;
            public String verificationCode;
            public boolean select;
            public boolean isSelect() {
                return select;
            }

            public void setSelect(boolean select) {
                this.select = select;
            }

        @Override
        public int getItemType() {
            return DepartAdapter.TYPE_LEVEL_1;
        }
    }
}
