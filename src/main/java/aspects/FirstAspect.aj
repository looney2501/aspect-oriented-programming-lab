package aspects;

public aspect FirstAspect {
    public FirstAspect(){
        System.out.println("First Aspect constructor!!!");
    }
     before():execution(public * repository..*(..)){
        System.out.println("Before executing "+thisJoinPointStaticPart.getSignature());
    }

     after():execution(public  * repository..*(..)){
        System.out.println("After executing "+thisJoinPointStaticPart.getSignature());
    }
}
