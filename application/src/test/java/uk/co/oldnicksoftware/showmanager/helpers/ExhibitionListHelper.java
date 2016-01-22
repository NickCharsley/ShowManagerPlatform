/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.oldnicksoftware.showmanager.helpers;

import javax.swing.tree.TreePath;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import org.netbeans.jellytools.MainWindowOperator;
import org.netbeans.jellytools.TopComponentOperator;
import org.netbeans.jellytools.nodes.Node;
import org.netbeans.jemmy.operators.ContainerOperator;
import org.netbeans.jemmy.operators.JButtonOperator;
import org.netbeans.jemmy.operators.JTreeOperator;

/**
 *
 * @author nick
 */
public class ExhibitionListHelper {
    private final String targetName;
    
    public ExhibitionListHelper(){
        targetName="ExhibitionList Window";
    }
    
    private TopComponentOperator componentOperator(){
        return  new TopComponentOperator(targetName);
    }
    
    private JTreeOperator treeOperator(){
        return new JTreeOperator(componentOperator());
    }
    
    private Node findNodeInList(String name){
        return findNodeInList(name,"");
    }
    
    private Node findNodeInList(String name,String subName){
        //N.B. we will cope with ' (Default)' at the end of the string...        
        JTreeOperator tree = treeOperator();        
        Node node=null;
        for (TreePath path :tree.getChildPaths(tree.findPath(""))){
            String szPath = path.getLastPathComponent().toString();
            if (szPath.equals(name) || szPath.equals(name.concat(" (Default)"))){
                if (!subName.equals("")){
                    for (TreePath subPath :tree.getChildPaths(path)){
                        szPath = subPath.getLastPathComponent().toString();
                        if (szPath.equals(subName)){
                            node=new Node(tree,subPath);
                            break;
                        }
                    }
                }
                else {
                    node=new Node(tree,path);
                }
                break;
            }
        }                    
        return node;
    }

    public void assertNodeInList(String name,String subName,boolean find){    
        assertThat("Found the "+name+" name",(findNodeInList(name,subName)!=null),is(find));                
    }

    
    public void assertNodeInList(String name,boolean find){    
        assertNodeInList(name,"",find);
    }
    
    
    public void assertNodeExpandable(String name,boolean expandable){
        Node node=findNodeInList(name);
        assertThat("Found the "+name+" name",node!=null,is(true));                
        assertThat(name+" is expandable",!(node.isLeaf()),is(expandable));
    }
    
    public void requestNodeEdit(String name){
        //At the moment it's just selects the node
        findNodeInList(name).select();
    }    
    
    public void requestNodeDelete(String name){
        String toolbar="&File";
        String item="Delete";
        
        findNodeInList(name).select();
        MainWindowOperator mwo = MainWindowOperator.getDefault();
        ContainerOperator tbo=mwo.getToolbar(toolbar);        
        assertThat("Found Toolbar "+toolbar,tbo,is(notNullValue()));
        JButtonOperator ibo=mwo.getToolbarButton(tbo, item);
        assertThat("Found "+item+" on toolbar "+toolbar,ibo,is(notNullValue()));
        ibo.push();        
    }
}
