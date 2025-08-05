package com.sube.plus.apaseo.sube_back.model;

import com.sube.plus.apaseo.sube_back.model.enums.DiseaseDetectionStatus;
import com.sube.plus.apaseo.sube_back.model.enums.EducationLevel;
import com.sube.plus.apaseo.sube_back.model.enums.MedicalAffiliationInstitute;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Document
public class SocioEconomic {

    @Id
    private String id;

    // Composicion Familiar
    private int householdMembers; // NumeroFamiliaVivienda
    private int numberOfWomen; // NumeroMujerHogar
    private int numberOfMen; // NumeroHombreHogar
    private boolean hasChildrenUnder18; // Menores18
    private boolean hasElderlyOver65; // Mayores65

    // Salud y Educacion
    private DiseaseDetectionStatus diseaseDetectionStatus; // EnfermedadesDetectadas
    private MedicalAffiliationInstitute medicalAffiliationInstitute; // InstitucionAfiliacionMedica (Catálogo)
    private boolean currentlyStudying; // ActualmenteEstudia
    private EducationLevel lastGradeCompleted; // UltimoGradoAprobado (Catálogo)

    // Ingresos
    private boolean currentlyWorking; // ActualmenteTrabaja
    private boolean hasJobBenefits; // PrestacionesLaborales
    private double mainMonthlyIncome; // IngresoTotalMensualPrincipalAportador
    private double otherMonthlyIncome; // OtrosIngresosMensuales
    private double totalMonthlyIncome; // IngresoTotalMensual

    // Gastos
    private double foodAndBeverageExpense; // GMAlimentoBebidas
    private double clothingAndFootwearExpense; // GMVestidoCalzado
    private double educationalSuppliesExpense; // GMArticulosServiciosEducativos
    private double medicineExpense; // GMMedicinas
    private double medicalConsultationExpense; // GMConsultasMedicas
    private double fuelExpense; // GMCombustible
    private double basicServicesExpense; // GMServiciosBasicos
    private double recreationExpense; // GMRecreacion

    // Datos de alimentacion
    private boolean littleVarietyInFood; // HuboPocaVariedadAlimento
    private boolean skippedMeals; // ComioMenosDebido
    private boolean reducedFoodPortions; // DisminuyoCantidadServida
    private boolean feltHungryButDidNotEat; // SintioHambreNoComio
    private boolean wentToBedHungry; // SeAcuestoConHambre
    private boolean ateOnceOrTwiceADay; // ComioUnaVezDiaDosVecDeComer


    //Datos de vivienda ----- Falta catalogo
    private int totalHouseholdSize; // TotalPersonasHogar
    private int totalRooms; // TotalCuartosVivienda
    private String housingTenureRegime; // RegimenTenenciaVivienda (Catálogo)
    private String predominantFloorMaterial; // MaterialPredominantePiso (Catálogo)
    private String predominantWallMaterial; // MaterialPredominantePared (Catálogo)
    private String predominantRoofMaterial; // MaterialPredominanteTecho (Catálogo)
    private String housingServices; // ServiciosHogar (Catálogo)
    private String kitchenFuelType; // CombustibleCocina (Catálogo)
    private String techApplianceOwnership; // ElectrodomesticoTecnologia (Catálogo)

}
