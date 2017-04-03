package kmitl.sp.smp.controller;

import kmitl.sp.smp.model.server.response.base.ApiBaseResponse;
import kmitl.sp.smp.service.ControllerService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

/**
 * Created by Jo on 4/3/2017.
 */
@RestController
@RequestMapping(value = "api/v1")
public class Controller {
    private final ControllerService controllerService;

    @Inject
    public Controller(ControllerService controllerService) {
        this.controllerService = controllerService;
    }

    @RequestMapping(value = "health", method = RequestMethod.GET)
    public boolean getHealth() {
        return true;
    }

    @RequestMapping(value = "suggested/{userId}")
    public ApiBaseResponse<?> getSuggestedSong(@PathVariable String userId) {
        return new ApiBaseResponse<>(HttpStatus.OK, controllerService.trainNewUserModelProfile(userId));
    }
}
