/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.oldnicksoftware.showmanager.steps.parked;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.runtime.PendingException;
import java.awt.event.KeyEvent;
import javax.swing.MenuElement;
import javax.swing.tree.TreePath;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import org.netbeans.jellytools.TopComponentOperator;
import org.netbeans.jellytools.nodes.Node;
import org.netbeans.jemmy.operators.JPopupMenuOperator;
import org.netbeans.jemmy.operators.JTreeOperator;

/**
 *
 * @author nick
 */
public class NodeSteps {
    private void z_nop() throws Throwable {
        // Just exists to keep PendingException as an import
        throw new PendingException();
    }   

    @Then("^(?:T|t)he \"([^\"]*)\" Panel's (ROOT|FIRST|LAST) NODE (has a|has no|doesnot have a|doesn't have a) (popup menu) item \"([^\"]*)\"$")
    public void nodeHasMenuItem(String panel,String node, String menuState, String menu, String item) throws Throwable {
        TopComponentOperator tco=  new TopComponentOperator(panel);        
        JTreeOperator tree = new JTreeOperator(tco);        
        TreePath path=tree.findPath("");
        Node opNode;
        if (node.equalsIgnoreCase("first")){
            opNode=new Node(tree,path);
            opNode=new Node(opNode,0);
            path=opNode.getTreePath();
        }
        else if (node.equalsIgnoreCase("last")){
            int children=tree.getChildCount(tree.getRoot());
            opNode=new Node(tree,path);
            opNode=new Node(opNode,children-1);
            path=opNode.getTreePath();            
        }
        boolean found=false;
        
        assertThat("Found the "+node,path,is(notNullValue()));
        JPopupMenuOperator popup = new JPopupMenuOperator(tree.callPopupOnPath(path));
        for (MenuElement me: popup.getSubElements()){
            String val=me.toString();
            if (val.contains(item)) {
                found=true;
                break;
            }
        }        
        popup.pushKey(KeyEvent.VK_ESCAPE);
        assertThat("Found the Menu Item "+item,found,is(menuState.equals("has a")));
    }
    
    @Then("^(?:T|t)he \"([^\"]*)\" Panel's (ROOT|FIRST|LAST) NODE is \"([^\"]*)\"$")
    @SuppressWarnings("null")
    public void nodeIs(String panel, String node,String name) throws Throwable {
        TopComponentOperator tco=  new TopComponentOperator(panel);        
        JTreeOperator tree = new JTreeOperator(tco);        
        TreePath path=tree.findPath("");
        Node opNode;
        if (node.equalsIgnoreCase("first")){
            opNode=new Node(tree,path);
            opNode=new Node(opNode,0);
        }
        else if (node.equalsIgnoreCase("last")){
            int children=tree.getChildCount(tree.getRoot());
            opNode=new Node(tree,path);
            opNode=new Node(opNode,children-1);
        }
        else {
            opNode=new Node(tree,path);
        }
        
        assertThat("Found the "+node,opNode,is(notNullValue()));
        assertThat("Node is "+name,opNode.getText(),is(name));
    }
    
    @Then("^(?:T|t)he \"([^\"]*)\" Panel's Node List (does not contain|contains) \"([^\"]*)\"$")
    public void nodeExists(String panel, String contains, String node) throws Throwable {
        TopComponentOperator tco=  new TopComponentOperator(panel);        
        JTreeOperator tree = new JTreeOperator(tco);        
        boolean found=false;
        for (TreePath path :tree.getChildPaths(tree.findPath(""))){
            String szPath = path.getLastPathComponent().toString();
            if (szPath.equals(node)){
               found=true;
               break;
            }
        }            
        assertThat("Found the "+node,found,is(contains.equalsIgnoreCase("contains")));
    }
        
    @When("^\"([^\"]*)\" is selected in the \"([^\"]*)\" Panel's Node List$")
    public void selectNodeList(String item, String panel) throws Throwable {
        TopComponentOperator tco=  new TopComponentOperator(panel);        
        JTreeOperator tree = new JTreeOperator(tco);                
        TreePath path=tree.findPath("");
        Node node;
        node=new Node(tree,path);
        if (!node.getText().equals(item)){
            node=new Node(tree,tree.findPath(item));
        }        
        node.select();
    }
    
    @Then("^(?:T|t)he \"([^\"]*)\" Panel contains (an empty|a) tree (of|with an?) \"([^\"]*)\"$")
    public void panelContainsTree(String panel, String empty, String ofAn, String entityType) throws Throwable {
        TopComponentOperator tco=  new TopComponentOperator(panel);        
        JTreeOperator tree = new JTreeOperator(tco);        
        
        int children=tree.getChildCount(tree.getRoot());
        
        int num = (empty.equals("a"))?1:0;
//        if (ofAn.equals("of"))
//            assertThat("Tree is "+empty+" tree "+ofAn+" "+entityType,children,is(not(0)));
//        else
            assertThat("Tree is "+empty+" tree "+ofAn+" "+entityType,children,is(num));
    }    
}