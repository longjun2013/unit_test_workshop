package com.longjun.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "t_user_filtered_for_action")
public class UserFilteredForAction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private int userId;
    private Date registryTime;
    private int status;
    private int filterType;
    private Date actionTime;

    protected UserFilteredForAction() {
    }

    public UserFilteredForAction(int userId, Date registryTime, Status status, Date actionTime) {
        this.userId = userId;
        this.registryTime = registryTime;
        this.status = status.getValue();
        this.actionTime = actionTime;
    }

    public Date getActionTime() {
        return actionTime;
    }

    public void setActionTime(Date actionTime) {
        this.actionTime = actionTime;
    }

    public FilterType getFilterType() {
        return FilterType.parse(this.filterType);
    }

    public void setFilterType(FilterType filterType) {
        this.filterType = filterType.getValue();
    }

    public Status getStatus() {
        return Status.parse(this.status);
    }

    public void setStatus(Status status) {
        this.status = status.getValue();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getRegistryTime() {
        return registryTime;
    }

    public void setRegistryTime(Date registryTime) {
        this.registryTime = registryTime;
    }

    @Override
    public String toString() {
        return "UserFilteredForAction{" +
                "id=" + id +
                ", userId=" + userId +
                ", registryTime=" + registryTime +
                ", status=" + status +
                ", filterType=" + filterType +
                ", actionTime=" + actionTime +
                '}';
    }

    public enum Status {
        TO_BE_ACTION(0), ACTION_COMPLETE(1), ACTION_FAILED(2);

        private int value;

        Status(int value) {
            this.value = value;
        }

        public static Status parse(int id) {
            Status status = null; // Default
            for (Status item : Status.values()) {
                if (item.getValue() == id) {
                    status = item;
                    break;
                }
            }
            return status;
        }

        public int getValue() {
            return value;
        }

    }

    public enum FilterType {
        /**
         * 距 注册后 等于 1 天
         */
        ONE(1),
        /**
         * 距 注册后 等于 7 天 && 病历数 小于 3
         */
        TWO(2),
        /**
         * 距 上次打开APP后 等于 14 天 && 距 注册后 大于 14 天 && 病历数 小于 20
         */
        THREE(3);

        private int value;

        FilterType(int value) {
            this.value = value;
        }

        public static FilterType parse(int id) {
            FilterType filterType = null; // Default
            for (FilterType item : FilterType.values()) {
                if (item.getValue() == id) {
                    filterType = item;
                    break;
                }
            }
            return filterType;
        }

        public int getValue() {
            return value;
        }
    }

}
