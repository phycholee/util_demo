package com.llf.demo.java.concurrent;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.concurrent.CountDownLatch;

/**
 * @author: Oliver.li
 * @Description:
 * @date: 2019/12/19 14:59
 */
public class IPlusTest implements Runnable {

    private int i;

    private CountDownLatch countDownLatch = new CountDownLatch(1000);

    @Override
    public void run() {
        i++;
        countDownLatch.countDown();
    }

//    public static void main(String[] args) throws InterruptedException {
//
//        IPlusTest test = new IPlusTest();
//
//        for (int i = 0; i < 1000; i++){
//            new Thread(test).start();
//        }
//
//        test.countDownLatch.await();
//        System.out.println(test.i);
//    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        String url = "http://vodactivity.lizhifm.com/static/html-to-image-templates/index.html?name=annual-event&data={\"userInfo\":{\"band\":\"142352770\",\"photo\":\"http://cdnoffice.lizhi.fm/user/2019/11/06/2769817421280875010.jpg\",\"userName\":\"回头不看了\uD83D\uDC7B\uD83D\uDC7B\"},\"awardList\":[{\"awardType\":6,\"icon\":\"http://cdnimg103.lizhi.fm/vod_wx/2019/12/17/2777392051403458637.png\",\"name\":\"心动声音主播金奖\",\"needRank\":0,\"suona\":0,\"voiceType\":0},{\"awardType\":1,\"icon\":\"http://cdnimg103.lizhi.fm/vod_wx/2019/12/17/2777391869468505677.png\",\"name\":\"忠粉热度声音主播\",\"needRank\":0,\"suona\":0,\"voiceType\":0}]}";

        String encode = URLEncoder.encode(url, "UTF-8");
        System.out.println(encode);
    }

}
