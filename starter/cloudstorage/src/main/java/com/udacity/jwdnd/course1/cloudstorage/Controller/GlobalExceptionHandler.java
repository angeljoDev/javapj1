package com.udacity.jwdnd.course1.cloudstorage.Controller;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handleDataIntegrityViolation(DataIntegrityViolationException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("Data integrity error occurred, please adjust your input.");
    }
    @ExceptionHandler(value = Exception.class)
    public String handleGeneralException(Exception e, Model model) {
        logger.error("Unhandled exception occurred:", e);
        model.addAttribute("error", "An error occurred: " + e.getMessage());
        return "error";  // Ensure you have an error.html template
    }
}
