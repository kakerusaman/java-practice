package com.github.kakerusaman.model;

import java.io.Serializable;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * カスタマーユーザー情報
 * CUser
 */
@Getter 
@Setter 
@ToString 
@Component
// springにセッションごとに別々に作成してくださいとお願いする設定 
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CUser implements Serializable {

    /**
     * シリアルバージョン
     */
    private static final long serialVersionUID = 1L;

    /**
     * スキーマ名
     */
    private String schemaName;
}
