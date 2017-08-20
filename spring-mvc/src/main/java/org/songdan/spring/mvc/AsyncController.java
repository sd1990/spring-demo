package org.songdan.spring.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by SongDan on 2017/8/20.
 */
@RequestMapping("async")
@Controller
public class AsyncController {

    private Map<String,DeferredResult<String>> deferredmap = new ConcurrentHashMap<>();

    @RequestMapping("callable")
    @ResponseBody
    public Callable<String> callable() {
        Callable<String> asyncResult = new Callable<String>() {

            @Override
            public String call() throws Exception {
                return "hello callable!";
            }
        };
        return asyncResult;
    }

    @RequestMapping("defer")
    @ResponseBody
    public DeferredResult<String> deferable() {
        DeferredResult<String> deferredResult = new DeferredResult<>();
        String key = "123";
        deferredResult.onCompletion(()-> {
            deferredmap.remove(key);
        });
        deferredmap.put(key, deferredResult);
        return deferredResult;
    }

    @RequestMapping("event/{id}")
    @ResponseBody
    public void event(@PathVariable String id) throws InterruptedException {
        DeferredResult<String> deferredResult = deferredmap.get(id);
        deferredResult.setResult("Hello DeferredResult");
    }

}
