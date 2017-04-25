package com.raytheon.mavenproject1;

import java.io.File;
import org.gephi.graph.api.Column;
import org.gephi.graph.api.DirectedGraph;
import org.gephi.graph.api.GraphController;
import org.gephi.graph.api.GraphModel;
import org.gephi.graph.api.Node;
import org.gephi.io.importer.api.Container;
import org.gephi.io.importer.api.EdgeDirectionDefault;
import org.gephi.io.importer.api.ImportController;
import org.gephi.io.processor.plugin.DefaultProcessor;
import org.gephi.project.api.ProjectController;
import org.gephi.project.api.Workspace;
import org.openide.util.Lookup;
import org.apache.commons.io.FilenameUtils;
import com.raytheon.statistics.plugin.LogicDistance;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HeadlessSimple {
    
    private static final Logger LOG = Logger.getLogger("com.raytheon.mavenproject1");

    public void script(String fileName) {
//        LOG.log(Level.INFO, "HeadlessSimple.script()");
//        LOG.log(Level.INFO, "  fileName = {0}", fileName);
        
        //Init a project - and therefore a workspace
        ProjectController pc = Lookup.getDefault().lookup(ProjectController.class);
        pc.newProject();
        Workspace workspace = pc.getCurrentWorkspace();

        //Get models and controllers for this new workspace - will be useful later
        GraphModel graphModel = Lookup.getDefault().lookup(GraphController.class).getGraphModel();
        ImportController importController = Lookup.getDefault().lookup(ImportController.class);

        String graphName = "unknown";
        
        //Import file       
        Container container;
        try {
//            File file = new File(getClass().getResource("/com/raytheon/mavenproject1/bbara.gexf").toURI());
            File file = new File(fileName);
            container = importController.importFile(file);
            container.getLoader().setEdgeDefault(EdgeDirectionDefault.DIRECTED);   //Force DIRECTED
            graphName = FilenameUtils.getBaseName(file.getName());
        } catch (Exception ex) {
            ex.printStackTrace();
            return;
        }

        //Append imported data to GraphAPI
        importController.process(container, new DefaultProcessor(), workspace);

        System.out.println("Graph: " + graphName);
        
        //See if graph is well imported
        DirectedGraph graph = graphModel.getDirectedGraph();
        System.out.println("Nodes: " + graph.getNodeCount());
        System.out.println("Edges: " + graph.getEdgeCount());

        //Get Centrality
        LogicDistance distance = new LogicDistance();
        distance.setDirected(true);
        distance.execute(graphModel);
        
        //List node columns
//        GraphModel model = Lookup.getDefault().lookup(GraphController.class).getGraphModel();
//        for (Column col : model.getNodeTable()) {
//            System.out.println(col);
//        }
        
        //Iterate values - fastest
//        Column centralityColumn = graphModel.getNodeTable().getColumn(LogicDistance.BETWEENNESS);
//        for (Node n : graphModel.getGraph().getNodes()) {
//            System.out.println(n.getAttribute(centralityColumn));
//        }
    }
}
