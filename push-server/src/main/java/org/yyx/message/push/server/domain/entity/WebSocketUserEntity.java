package org.yyx.message.push.server.domain.entity;

import cn.hutool.core.util.StrUtil;

import java.util.Objects;

/**
 * 用户实体对象
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2022/4/7 21:01
 */
public class WebSocketUserEntity {

    private String id;

    private String userName;

    private String userAvatar;

    private String client;

    public WebSocketUserEntity(String id, String userName, String userAvatar, String client) {
        this.id = id;
        this.userName = userName;
        this.userAvatar = userAvatar;
        this.client = client;
    }

    public WebSocketUserEntity(String id, String userName, String userAvatar) {
        this.id = id;
        this.userName = userName;
        this.userAvatar = userAvatar;
    }

    public WebSocketUserEntity(String id, String userName) {
        this.id = id;
        this.userName = userName;
    }

    public WebSocketUserEntity(String id) {
        this.id = id;
    }

    public WebSocketUserEntity() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        WebSocketUserEntity that = (WebSocketUserEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(userName, that.userName) && Objects.equals(userAvatar,
                that.userAvatar) && Objects.equals(client, that.client);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userName, userAvatar, client);
    }

    @Override
    public String toString() {
        return "WebSocketUserEntity{" + "id='" + id + '\'' + ", userName='" + userName + '\'' + ", userAvatar='" + userAvatar + '\'' + ", client='" + client + '\'' + '}';
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    /**
     * 唯一标识 。如果id为空，则使用用户名
     *
     * @return 唯一标识
     */
    public String uniqueKey() {
        if (StrUtil.isAllNotBlank(id)) {
            return id;
        } else if (StrUtil.isAllNotBlank(userName)) {
            return userName;
        }
        return null;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }
}
