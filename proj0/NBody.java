public class NBody {
	public static double radius;
	public static Body[] bodies;
	public static String filename;
	public static double T;
	public static double dt;
	public static double time = 0;
	public static int N;

	public static double readRadius(String s) {
		In in = new In(s);
		N = in.readInt();
		double R = in.readDouble();
		return R;
	}

	public static Body[] readBodies(String s) {
		In in = new In(s);
		N = in.readInt();
		double R = in.readDouble();
		String S;
		Body[] Bodys = new Body[N];
		for (int i = 0; i < N; i++) {
			double[] Params = new double[5];
			for (int j = 0; j < 5; j++) {
				Params[j] = in.readDouble();
			}
			S = in.readString();
			Bodys[i] = new Body(Params[0], Params[1], Params[2], Params[3], Params[4], S);
		}
		return Bodys;
	}

	public static void main(String[] args) {
		T = Double.parseDouble(args[0]);
		dt = Double.parseDouble(args[1]);
		filename = args[2];
		radius = NBody.readRadius(filename);
		bodies = NBody.readBodies(filename);
		StdDraw.enableDoubleBuffering();
		while (time <= T) {
			double[] xForces = new double[N];
			double[] yForces = new double[N];
			for (int i = 0; i < bodies.length; i++) {
				xForces[i] = bodies[i].calcNetForceExertedByX(bodies);
				yForces[i] = bodies[i].calcNetForceExertedByY(bodies);
			}
			for (int i = 0; i < bodies.length; i++) {
				bodies[i].update(dt, xForces[i], yForces[i]);
			}
			drawbackground();
			for (int i = 0; i < bodies.length; i++) {
				bodies[i].draw();
			}
			StdDraw.show();
			StdDraw.pause(10);
			time += dt;
		}
		StdOut.printf("%d\n", bodies.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < bodies.length; i++) {
    		StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                 bodies[i].xxPos, bodies[i].yyPos, bodies[i].xxVel,
                 bodies[i].yyVel, bodies[i].mass, bodies[i].imgFileName);   
}
	}

	public static void drawbackground() {
		String imageToDraw = "images/starfield.jpg";
		StdDraw.setScale(-radius, radius);
		StdDraw.clear();
		StdDraw.picture(0, 0, imageToDraw);
	}
}