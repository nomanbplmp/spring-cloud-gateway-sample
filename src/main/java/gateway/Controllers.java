package gateway;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.WebSession;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class Controllers {


    @PostMapping("/hello")
    public Map<String, String> hello(@RequestBody String body) {
        Map<String, String> map = new HashMap<>();
        map.put("one", "two");
        if(StringUtils.isNotEmpty(body)){
            map.put("req",body);
        }
        return map;
    }

    @GetMapping("/hello1")
    public Map<String, String> hello(@RequestParam Map<String,String> map, WebSession session) {
        System.out.println(session.isStarted());
    //   Map<String,String[]> map =  httpServletRequest.getParameterMap();


        return map;
    }

}
