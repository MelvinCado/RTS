package game.main;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

/**
 * cree un objet carre.
 * 
 * @author Melvin
 *
 */
public class Carre extends Pane{

	private Controller controller;
	
	private double longueur = 10.0;
	private boolean move;
	private boolean selected;
	private Color couleurInit;
	private Color couleur;
	private double speed;
	private double coeffX;
	private double coeffY;
	private double destinationX;
	private double destinationY;
	private boolean destFini;
	private Rectangle carreMiniMap;
	private Rectangle rect;
	
	// caracteristique de la troupe 
	
	private final double TRUESPEED = 2;
	private double pv;
	private Rectangle zoneAttack;
	
	private final Duration duree = Duration.millis(3000); // Une dur�e en millisecondes. 
	
	/**
	 * Ce constructeur cree le carre dans la position donne.
	 * 
	 * @param x la position en x.
	 * @param y la position en y.
	 * @param couleur la couleur de l'objet voulu.
	 * @param controller le controlleur.
	 */
	public Carre(double x, double y,Color couleur, Controller controller) {
		super();
		super.setTranslateX(x);
		super.setTranslateY(y);
		super.setWidth(this.getLongueur());
		super.setHeight(this.getLongueur());
		
		this.controller = controller;
		this.couleur = couleur;
		this.couleurInit = couleur;
		rect = new Rectangle(longueur,longueur,couleur);
		this.destFini = true;
		this.selected = false;
		this.move = false;
		this.speed =2 ;
		
		this.pv = 100;
		this.zoneAttack = new Rectangle();
		this.zoneAttack.setTranslateX(-2);
		this.zoneAttack.setTranslateY(-2);
		this.zoneAttack.setWidth(this.getWidth()+ 4);
		this.zoneAttack.setHeight(this.getHeight()+4);
		this.zoneAttack.setFill(Color.TRANSPARENT);
		this.zoneAttack.setStroke(Color.WHITE);
		
		this.getChildren().add(zoneAttack);
		this.getChildren().add(rect);
		
	}

	/**
	 * Cette methode contient les elements a mettre a jour a chaque tour de boucle de jeu.
	 */
	public void uptdate() {
		
		if(this.getPv() <=0 ) {
			
			this.controller.getListCarre().remove(this);
			
		}
		Physics.ZoneAttackCarre(this, controller.getListCarre());
		Physics.CollisionCarreVSCarre(this, controller.getListCarre()); // �a bug cherche pourquoi !!
		
		carreMiniMap.setTranslateX(this.getTranslateX()*controller.getCoeffMiniMap());
		carreMiniMap.setTranslateY(this.getTranslateY()*controller.getCoeffMiniMap());
		
		if ( (int)this.getX() >=  (int)destinationX-1 && (int)this.getX() <= (int)destinationX+1
				&&  (int)this.getY() >=  (int)destinationY-1 && (int)this.getY()<= (int)destinationY+1) {
			this.setMove(false);
			setDestFini(true);
		} else {
				
			if (this.isMove()) {

				setDestFini(false);

				if (super.getTranslateX() < destinationX) {
					super.setTranslateX(super.getTranslateX() + speed * coeffX);
				} else if (super.getTranslateX() > destinationX) {
					super.setTranslateX(super.getTranslateX() - speed * coeffX);
				}
				if (super.getTranslateY() < destinationY) {
					super.setTranslateY(super.getTranslateY() + speed * coeffY);
				} else if (super.getTranslateY() > destinationY) {
					super.setTranslateY(super.getTranslateY() - speed * coeffY);
				}
			}
		}
	}
	
	public void Attack(Carre carre) {
		
		carre.setPv(carre.getPv()-100);
		
	}
	
	// getters/setters
	
	public double getX() {
		
		return super.getTranslateX();
	}
	public Rectangle getZoneAttack() {
		return zoneAttack;
	}
	public double getY() {
		
		return super.getTranslateY();
	}
	public boolean isMove() {
		return move;
	}
	public void setMove(boolean move) {
		this.move = move;
	}
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	public double getDestinationX() {
		return destinationX;
	}
	public double getDestinationY() {
		return destinationY;
	}
	public void setDestinationX(double destinationX) {
		this.destinationX = destinationX;
	}
	public void setDestinationY(double destinationY) {
		this.destinationY = destinationY;
	}
	public boolean isSelected() {
		return selected;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
		if(selected) {
			this.rect.setStroke(Color.YELLOW);
		}else {
			this.rect.setStroke(null);
		}
	}
	public double getCoeffX() {
		return coeffX;
	}
	public void setCoeffX(double coeffX) {
		this.coeffX = coeffX;
	}
	public double getCoeffY() {
		return coeffY;
	}
	public void setCoeffY(double coeffY) {
		this.coeffY = coeffY;
	}
	public double getLongueur() {
		return longueur;
	}
	public boolean isDestFini() {
		return destFini;
	}
	public void setDestFini(boolean destFini) {
		this.destFini = destFini;
	}
	public double getTRUESPEED() {
		return TRUESPEED;
	}
	public Rectangle getRect() {
		return rect;
	}
	public void setLongueur(double longueur) {
		this.longueur = longueur;
		this.setPrefWidth(longueur);
		this.setPrefHeight(longueur);
		this.getRect().setWidth(longueur);
		this.getRect().setHeight(longueur);
		
	}
	public void setCarreMiniMap(Rectangle carreMiniMap) {

		this.carreMiniMap = carreMiniMap;
	}
	public Rectangle getCarreMiniMap() {
		return carreMiniMap;
	}
	public boolean getSelected() {
		return selected;
	}
	public double getPv() {
		return pv;
	}
	public void setPv(double pv) {

		this.pv = pv;
	}
}
