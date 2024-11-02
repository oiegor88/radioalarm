package io.radioalarm.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ConsoleController {

  @GetMapping
  public String console() {
    return "index.html";
  }
}
