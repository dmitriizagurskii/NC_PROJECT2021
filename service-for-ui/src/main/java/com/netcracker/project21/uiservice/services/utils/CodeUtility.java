package com.netcracker.project21.uiservice.services.utils;


import com.netcracker.project21.uiservice.domain.CodeChangeInfo;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class CodeUtility {


    // Changing text
    public void applyChange(StringBuilder text, CodeChangeInfo changeInfo) {
        WrapInt start = new WrapInt();
        WrapInt end = new WrapInt();

        transformCoordinates(text, changeInfo.getFrom(), changeInfo.getTo(), start, end);

        if (start.value == end.value && changeInfo.getText() == "") {
            text.replace(start.value, end.value, "\n");
        } else {
            text.replace(start.value, end.value, changeInfo.getText());
        }
        System.out.println("Updated text: " + text);
    }

    // transforming coordinates: {from: {line: int, ch: int}, to: {line: int, ch: int}} -> {start, end}
    private void transformCoordinates(StringBuilder text, CodeChangeInfo.From from, CodeChangeInfo.To to,
                                     WrapInt start, WrapInt end) {
        if (to.getLine() == 0) {
            start.value = from.getCh();
            end.value = to.getCh();
        } else {
            List<Integer> newLineCoordinates = new LinkedList<>();
            int newLineIndex = text.indexOf("\n");
            newLineCoordinates.add(newLineIndex);

            while ((newLineIndex = text.indexOf("\n", newLineIndex + 1)) != -1) {
                newLineCoordinates.add(newLineIndex);
            }

            if (from.getLine() == 0) {
                start.value = to.getCh();
            } else {
                start.value = newLineCoordinates.get(from.getLine() - 1) + from.getCh() + 1;
            }

            end.value = newLineCoordinates.get(to.getLine()-1) + to.getCh() + 1;

        }




    }

}
