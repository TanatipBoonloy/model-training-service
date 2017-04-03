package kmitl.sp.smp.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Jo on 4/3/2017.
 */
@RestController
@RequestMapping(value = "api/v1/smp")
public class Controller {
    @RequestMapping(value = "health", method = RequestMethod.GET)
    public boolean getHealth() {
        return true;
    }
}
