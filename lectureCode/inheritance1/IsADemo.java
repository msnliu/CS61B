public class IsADemo {
    public static void main(String[] args) {
        /** static type List61B dynamic type SLList */
        List61B<String> someList = new SLList<>();
        someList.addFirst("elk");
        someList.addLast("dwell");
        someList.addLast("on");
        someList.addLast("existential");
        someList.addLast("crises");

        /** dynamic method selection
        if dynamic type overrides the method, dynamic method will be used instead

         /** overload: everywhere + same name different signature
         override: inheritance + exact same signature */

        /** IMPORTANT: This does not work for overloaded methods!
         print() List61B
         print() SLList WORKS for overriding

         peek(SLList)
         peek(List61B) DO NOT WORK for overloading
         use exactly what we say we want */

        someList.print();
    }
}