package com.sube.plus.apaseo.sube_back.model.request;

import com.sube.plus.apaseo.sube_back.model.enums.DiseaseDetectionStatus;
import com.sube.plus.apaseo.sube_back.model.enums.EducationLevel;
import com.sube.plus.apaseo.sube_back.model.enums.MedicalAffiliationInstitute;
import com.sube.plus.apaseo.sube_back.model.enums.RegimenTenenciaVivienda;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SocioEconomicRequest {

    private int householdMembers;
    private int numberOfWomen;
    private int numberOfMen;
    private boolean hasChildrenUnder18;
    private boolean hasElderlyOver65;

    private DiseaseDetectionStatus diseaseDetectionStatus;
    private MedicalAffiliationInstitute medicalAffiliationInstitute;
    private boolean currentlyStudying;
    private EducationLevel lastGradeCompleted;

    private boolean currentlyWorking;
    private boolean hasJobBenefits;
    private double mainMonthlyIncome;
    private double otherMonthlyIncome;
    private double totalMonthlyIncome;

    private double foodAndBeverageExpense;
    private double clothingAndFootwearExpense;
    private double educationalSuppliesExpense;
    private double medicineExpense;
    private double medicalConsultationExpense;
    private double fuelExpense;
    private double basicServicesExpense;
    private double recreationExpense;

    private boolean littleVarietyInFood;
    private boolean skippedMeals;
    private boolean reducedFoodPortions;
    private boolean feltHungryButDidNotEat;
    private boolean wentToBedHungry;
    private boolean ateOnceOrTwiceADay;

    private int totalHouseholdSize;
    private int totalRooms;
    private RegimenTenenciaVivienda housingTenureRegime;
    private String predominantFloorMaterial;
    private String predominantWallMaterial;
    private String predominantRoofMaterial;
    private String housingServices;
    private String kitchenFuelType;
    private String techApplianceOwnership;

}