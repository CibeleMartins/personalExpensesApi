package br.com.personal.expenses.personalexpenses.domain.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.personal.expenses.personalexpenses.dto.Dashboard.DashboardResponseDTO;
import br.com.personal.expenses.personalexpenses.dto.Title.TitleResponseDTO;

@Service
public class DashboardService {

    @Autowired
    private TitleService titleService;

    public DashboardResponseDTO getCashFlow(String periodInitial, String periodFinal) {

        List<TitleResponseDTO> titles = titleService.getTitleByDateDue(periodInitial, periodFinal);

        Double totalPay = 0.0;

        Double totalReceive = 0.0;
        List<TitleResponseDTO> titlesPay = new ArrayList<>();

        List<TitleResponseDTO> titlesReceive = new ArrayList<>();

        Double balance = 0.0;

        for(TitleResponseDTO title : titles){

            if(title.getType() == "A PAGAR") {
                totalPay += title.getValue();
                titlesPay.add(title);
            }

            if(title.getType() == "A RECEBER") {
                totalReceive += title.getValue();
                titlesReceive.add(title);
            }
        }

        return new DashboardResponseDTO();
    }
}
