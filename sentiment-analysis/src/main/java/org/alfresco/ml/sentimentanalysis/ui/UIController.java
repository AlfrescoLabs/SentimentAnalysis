package org.alfresco.ml.sentimentanalysis.ui;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by cleseach on 29/01/2019.
 */

@Controller
public class UIController {

    @RequestMapping("/")
    public String index() {
        return "index";
    }
}