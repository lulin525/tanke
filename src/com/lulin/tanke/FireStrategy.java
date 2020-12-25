package com.lulin.tanke;

/**
 * 应用策略模式到坦克的fire打子弹
 *
 * @Author: LuLin
 * @Date: 2020/12/25 9:31
 */
public interface FireStrategy {

     void fire(Tanke t);//开火策略——打子弹

}
