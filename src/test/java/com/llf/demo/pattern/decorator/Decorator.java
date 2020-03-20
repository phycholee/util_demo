package com.llf.demo.pattern.decorator;

/**
 * @author: Oliver.li
 * @Description: 装饰抽象类
 * @date: 2020/3/20 14:44
 */
public abstract class Decorator implements Component {

    protected Component component;

    public void setComponent(Component component) {
        this.component = component;
    }


    @Override
    public void operation() {
        if (component != null){
            component.operation();
        }
    }
}
