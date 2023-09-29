public class Main {
    static class Ball {
        private String name;
        private int weight;
        private String type;

        public Ball(String name, int weight, String type) {
            this.name = name;
            this.weight = weight;
            this.type = type;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static void main(String[] args) {
        Ball soccerBall = new Ball("A", 50, "soccer ball");
        Ball baseBall = new Ball("T", 15, "baseball");

        System.out.println("soccerBall name is " + soccerBall.getName());
        System.out.println("baseBall name is " + baseBall.getType());
    }
}