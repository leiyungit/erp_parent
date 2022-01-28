package cn.itcast.erp.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * 树形菜单
 */
public class Tree {
    private String id;  //节点ID，对加载远程数据很重要。
    private String text;  //显示节点文本。
    private Boolean checked;  //表示该节点是否被选中。
    private List<Tree> children;  // 一个节点数组声明了若干节点。

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }
    // 给默认值
    public List<Tree> getChildren() {
        if(null == this.children){
            children = new ArrayList<>();
        }
        return children;
    }

    public void setChildren(List<Tree> children) {
        this.children = children;
    }
}
