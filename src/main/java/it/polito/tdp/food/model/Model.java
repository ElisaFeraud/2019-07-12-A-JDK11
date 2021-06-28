package it.polito.tdp.food.model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.food.db.FoodDao;


public class Model {
    FoodDao dao;
    Map<Integer,Food> idMap;
    Graph<Food,DefaultWeightedEdge> grafo;
    
    public Model() {
    	dao = new FoodDao();
    }
    
    public void creaGrafo(int n) {
    	idMap = new HashMap<>();
    	dao.getVertici(idMap, n);
    	this.grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
    	Graphs.addAllVertices(grafo, idMap.values());
    	for(Collegamento c: dao.getArchi(idMap)) {
    		if(this.grafo.containsVertex(c.getFood1()) && this.grafo.containsVertex(c.getFood2())) {
    			DefaultWeightedEdge e = this.grafo.getEdge(c.getFood1(),c.getFood2());
    			if(e==null) {
    				Graphs.addEdgeWithVertices(grafo,c.getFood1(),c.getFood2(),c.getPeso());
    			}
    		}
    	}
    }
    
    public String infoGrafo() {
		 return "Grafo creato con "+ this.grafo.vertexSet().size()+ " vertici e " + this.grafo.edgeSet().size()+" archi\n";
	 }
    
    public List<Food> getFoodVertici(){
    	List<Food> result = new LinkedList<>(this.grafo.vertexSet());
    	return result;
    	
    }
    
    public List<Corrispondenza> getAdiacenti(Food food){
    	List<Corrispondenza> prova = new LinkedList<>();
    	List<Corrispondenza> result = new LinkedList<>();
    	if(this.grafo.edgesOf(food).size()!=0) {
    	for(DefaultWeightedEdge d: this.grafo.edgesOf(food)) {
			Food f2=null;
			if(!this.grafo.getEdgeSource(d).equals(food) && this.grafo.getEdgeTarget(d).equals(food)) {
			   f2 = this.grafo.getEdgeSource(d);}
			else if(this.grafo.getEdgeSource(d).equals(food) && !this.grafo.getEdgeTarget(d).equals(food))
				 f2 =this.grafo.getEdgeTarget(d);
			  double peso =  this.grafo.getEdgeWeight(d);
			  Corrispondenza c= new Corrispondenza(f2,peso);
			  prova.add(c);
			 
		}
    	prova.sort(new CorrispondenzaPerPeso().reversed());
    	if(prova.size()<=5) {
    	for(int i=0; i<prova.size(); i++) {
    		
    		 result.add(prova.get(i));
    		
    	}}
    	else {
    		for(int i=0; i<5; i++) {
    			
   		         result.add(prova.get(i));
   		
   	}}
    		
    	return result;
    	}
    	return null;
    }
}
