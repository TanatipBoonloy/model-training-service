package kmitl.sp.smp.controller;

import kmitl.sp.smp.model.server.response.base.ApiBaseResponse;
import kmitl.sp.smp.service.ControllerService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;

/**
 * Created by Jo on 4/3/2017.
 */
@CrossOrigin(origins = "*", maxAge = 3600)
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

    @RequestMapping(value = "suggested/{userId}", method = RequestMethod.GET)
    public ApiBaseResponse<?> getSuggestedSong(@PathVariable String userId) {
        return new ApiBaseResponse<>(HttpStatus.OK, controllerService.getUserSuggestedMusic(userId));
    }
}
