package com.lkeren.bean;

/**
 * Created with IntelliJ IDEA
 *
 * @Author : lkeren
 * @Date : 2024/03/25/17:14
 * @Description :
 */

// 用户和管理员都是属于用户类
public class User {
    private String signTime;  // 开户时间
    private int accountCard;  // 账户卡号,如果用户输入了卡号就用用户的卡号，如果没有输入就用身份证hashcode()过来，考虑的有点不好，但是因为登录注册写一块了，所以没办法单独输入身份证了
    private String accountName;  // 账户名称
    private String mobile;  // 手机号码
    private String IDcard;  // 身份证
    private  String password;  // 密码，在控制台显示“*”号码
    private double balance;  // 账户余额
    private int type;  // 用户类型  0-管理员，1-用户

    public User() {
    }

    public User(int accountCard, String password) {
        this.accountCard = accountCard;
        this.password = password;
    }

    public User(int accountCard) {
        this.accountCard = accountCard;
    }

    public User(int accountCard, String accountName, String mobile, String IDcard, String password) {
        this.accountCard = accountCard;
        this.accountName = accountName;
        this.mobile = mobile;
        this.IDcard = IDcard;
        this.password = password;
    }

    public User(int accountCard, String accountName, String mobile, String IDcard, String password, double balance) {
        this.accountCard = accountCard;
        this.accountName = accountName;
        this.mobile = mobile;
        this.IDcard = IDcard;
        this.password = password;
        this.balance = balance;
    }

    public User(int accountCard, String accountName, String mobile, String IDcard, String password, double balance, int type) {
        this.accountCard = accountCard;
        this.accountName = accountName;
        this.mobile = mobile;
        this.IDcard = IDcard;
        this.password = password;
        this.balance = balance;
        this.type = type;
    }

    public User(String sign_time, int accountCard, String accountName, String mobile, String IDcard, String password, double balance, int type) {
        this.signTime = sign_time;
        this.accountCard = accountCard;
        this.accountName = accountName;
        this.mobile = mobile;
        this.IDcard = IDcard;
        this.password = password;
        this.balance = balance;
        this.type = type;
    }

    public User(String accountName, String mobile, String IDcard, String password) {
        this.accountName = accountName;
        this.mobile = mobile;
        this.IDcard = IDcard;
        this.password = password;
    }



    public String getSignTime() {
        return signTime;
    }

    public void setSignTime(String signTime) {
        this.signTime = signTime;
    }

    public int getAccountCard() {
        return accountCard;
    }

    public void setAccountCard(int accountCard) {
        this.accountCard = accountCard;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getIDcard() {
        return IDcard;
    }

    public void setIDcard(String IDcard) {
        this.IDcard = IDcard;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "User{" +
                "sign_time='" + signTime + '\'' +
                ", accountCard=" + accountCard +
                ", accountName='" + accountName + '\'' +
                ", mobile='" + mobile + '\'' +
                ", IDcard='" + IDcard + '\'' +
                ", password='" + password + '\'' +
                ", balance=" + balance +
                ", type=" + type +
                '}';
    }
}
