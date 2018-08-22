//package ru.isys.groupwagering;
//
//import org.junit.Ignore;
//import org.junit.Test;
//
//import static org.junit.Assert.*;
//
//import org.junit.runner.RunWith;
//import org.junit.runners.Parameterized;
//import  ru.isys.groupwagering.MainMath;
//
//import java.util.Arrays;
//import java.util.Collection;
//import java.util.Collections;
//
//@RunWith(Parameterized.class)
//public class MainMathTest {
//    int a,b,expResult;
//
//    public MainMathTest(int a, int b, int expResult) {
//        this.a = a;
//        this.b = b;
//        this.expResult = expResult;
//    }
//
//    MainMath mainMath=new MainMath();
//    @Parameterized.Parameters
//    public static Collection<Object[]> numbers(){
//        return Arrays.asList(new Object[][]{{1,2,3},{2,0,2},{3,5,8}});
//    }
//
//    @Test
//    public void sum() {
////        int a=2;
////        int b=3;
////        int expResult=5;
//        int result=mainMath.sum(a,b);
//        assertEquals(expResult,result);
//    }
//
//    @Ignore
//    @Test(expected = ArithmeticException.class)
//    public void div() {
//        int result=mainMath.div(a,b);
//        assertEquals(expResult,result);
//    }
//}