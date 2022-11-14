package com.wallet.app.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.*;
import org.junit.Test;
import com.wallet.app.dto.Wallet;
import com.wallet.app.exception.WalletException;

public class WalletServiceImplTest {
    static WalletService walletServiceTest = new WalletServiceImpl();
    @Test
    public void registerWalletTest()  {
        try{
            Wallet wallet1 = new Wallet(110, "ford", 1000.0, "098");
            Wallet wallet2 = new Wallet(109, "car", 100.0, "678");
            assertEquals(wallet1,walletServiceTest.registerWallet(wallet1));
            assertEquals(wallet2,walletServiceTest.registerWallet(wallet2));
            assertNotNull(wallet1);
            assertNotNull(wallet2);
        }catch (Exception e){
             System.out.println(e.getMessage());
        }
    }
    @Test
    
    public void LoginTest()  {
        try{
            assertTrue(walletServiceTest.login(111,"12345"));
        }catch (Exception e){
           System.out.println(e.getMessage());
        }
    }
    @Test
    
    public void AddFundsToWalletsTest()  {
        try{
        	boolean temp=false;
        	if(walletServiceTest.addFundsToWallet(110,10.0)==1010.0) {
        		temp=true;
        	}
            assertEquals(true,temp);
        }catch (Exception e){
             System.out.println(e.getMessage());
        }
    }
    @Test
    
    public void ShowWalletBalanceTest()  {
        try{
        	double temp=walletServiceTest.showWalletBalance(109);
        	System.out.println(temp);
            assertEquals(true,walletServiceTest.showWalletBalance(109));
            assertThrows(WalletException.class,()-> walletServiceTest.showWalletBalance(200));
        }catch (Exception e){
             System.out.println(e.getMessage());
        }
    }
    @Test
    
    public void FundTransfer()  {
        try{
            assertEquals(true, walletServiceTest.fundTransfer(109, 110, 10.0));
        }catch (Exception e){
             System.out.println(e.getMessage());
        }
    }
//    @Test
//    
//    public void UnRegisterWallet()  {
//        try{
//            assertEquals(null,walletServiceTest.unRegisterWallet(110,"098"));
//            assertEquals(null,walletServiceTest.unRegisterWallet(109,"678"));
//        }catch (Exception e){
//             System.out.println(e.getMessage());
//        }
//    }

}
