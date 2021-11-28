public class Body {
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;

	public Body(double xP, double yP, double xV, double yV, double m, String img) {
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}

	public Body(Body b) {
		xxPos = b.xxPos;
		yyPos = b.yyPos;
		xxVel = b.xxVel;
		yyVel = b.yyVel;
		mass = b.mass;
		imgFileName = b.imgFileName;
	}

	public double calcDistance(Body ref) {
		double dist;
		dist = Math.sqrt(Math.pow(this.xxPos - ref.xxPos, 2) + Math.pow(this.yyPos - ref.yyPos, 2));
		return dist;
	}

	public double calcForceExertedBy(Body ref) {
		double force;
		double distsquared;
		double G = 6.67e-11;
		if (this.equals(ref)) {
			return 0;
		}
		distsquared = Math.pow(this.calcDistance(ref), 2);
		force = G * this.mass * ref.mass / distsquared;
		return force;
	}

	public double calcForceExertedByX(Body ref) {
		double xforce;
		xforce = this.calcForceExertedBy(ref) * (ref.xxPos - this.xxPos) / this.calcDistance(ref);
		return xforce;
	}

	public double calcForceExertedByY(Body ref) {
		double yforce;
		yforce = this.calcForceExertedBy(ref) * (ref.yyPos - this.yyPos) / this.calcDistance(ref);
		return yforce;
	}

	public double calcNetForceExertedByX(Body[] ref) {
		double xnet = 0;
		for (Body b : ref) {
			if (this.equals(b)) {
				continue;
			} else {
				xnet += this.calcForceExertedByX(b);
			}
		}
		return xnet;
	}

	public double calcNetForceExertedByY(Body[] ref) {
		double ynet = 0;
		for (Body b : ref) {
			if (this.equals(b)) {
				continue;
			} else {
				ynet += this.calcForceExertedByY(b);
			}
		}
		return ynet;
	}

	public void update(double dt, double fX, double fY) {
		double anetx;
		double anety;
		anetx = fX / this.mass;
		anety = fY / this.mass;
		this.xxVel += dt * anetx;
		this.yyVel += dt * anety;
		this.xxPos += dt * this.xxVel;
		this.yyPos += dt * this.yyVel;
	}

	public void draw() {
		StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
	}
}