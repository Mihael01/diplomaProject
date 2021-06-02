package com.health.SchoolHealth.util;

import com.health.SchoolHealth.controlers.formPOJOs.AddressForm;
import com.health.SchoolHealth.model.entities.Address;
import com.health.SchoolHealth.model.entities.Municipality;
import com.health.SchoolHealth.model.entities.SettlementPlace;
import com.health.SchoolHealth.services.AddressService;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FormUtil {

    public static final String ADDRESS_ABOUT_SCHOOL = "учебното заведение";
    public static final String ADDRESS_ABOUT_STUDENT = "ученика";
    public static final String ADDRESS_ABOUT_PARENT = "родителя";
    public static final String CONFIRM_FLAG = "Y";

    public static AddressForm setAddressForm(String addressAbout, Long addressId, Long addressId2, AddressService addressService,
                                             AddressForm addressForm, HttpSession httpSession) {
        System.out.println("!!!!!!!!!!!!!httpSession  municipalityCode2 " + httpSession.getAttribute("municipalityCode2"));
        System.out.println("!!!!!!!!!!!!!httpSession  municipalityCode " + httpSession.getAttribute("municipalityCode"));
        System.out.println("!!!!!!!!!!!!!httpSession  regionCode2 " + httpSession.getAttribute("regionCode2"));
        System.out.println("!!!!!!!!!!!!!httpSession regionCode " + httpSession.getAttribute("regionCode"));

        httpSession.setAttribute("addressAbout", addressAbout);
//
        Address foundAddress = setAddress(addressId, addressService, addressForm);
        if (foundAddress != null) {
            System.out.print("foundAddress " + foundAddress.getId());
        }

        Address foundAddress2 = setAddress2(addressId2, addressService, addressForm);
        if (foundAddress2 != null) {
            System.out.print("foundAddress2 " + foundAddress2.getId());
        }

        // Елементи за адреса
        // на училище, на ученик, на родител
        addressForm.setAddressAbout(addressAbout);

        // Област
        String regionCode = setRegionCode(addressForm, httpSession, foundAddress);
        String regionCode2 = setRegionCode2(addressForm, httpSession, foundAddress2);

        addressForm.setRegions(addressService.getAllRegions());

        // Община
        String municipalityCode = setMunicipalityCode(addressForm, httpSession, foundAddress);
        String municipalityCode2 = setMunicipalityCode2(addressForm, httpSession, foundAddress2);

        if (regionCode != null) {
            System.out.println();
            addressForm.setMunicipalities(addressService.getAllMunicipalitiesInRegion(regionCode));
            System.out.println("Municipalities size for region code " + addressForm.getMunicipalities().size() + " regionCode "); //да се види тук лога и защо не вади общините
        }
        if (regionCode2 != null) {
            addressForm.setMunicipalities2(addressService.getAllMunicipalitiesInRegion(regionCode2));
        }

        List<String> municipalityCodes = CollectionUtils.isEmpty(addressForm.getMunicipalities()) ? new ArrayList<>() :
                addressForm.getMunicipalities().stream().map(Municipality::getMunicipalityCode).collect(Collectors.toList());

        if (municipalityCodes.size() > 0 && !municipalityCodes.contains(municipalityCode)) {
            if (regionCode != null) {
                municipalityCode = municipalityCodes.get(0);
            }
        }
System.out.println("before Municipalities2 municipalityCode2   " + municipalityCode2);
        List<String> municipalityCodes2 = CollectionUtils.isEmpty(addressForm.getMunicipalities2()) ? new ArrayList<>() :
                addressForm.getMunicipalities2().stream().map(Municipality::getMunicipalityCode).collect(Collectors.toList());

        if (municipalityCodes2.size() > 0 && !municipalityCodes2.contains(municipalityCode2)) {
            if (regionCode2 != null) {
                municipalityCode2 = municipalityCodes2.get(0);
            }
        }
        System.out.println("after Municipalities2 municipalityCode2   " + municipalityCode2);
        // Селище
        List<String> allSettlementPlaceType = new ArrayList<>();
        allSettlementPlaceType.add("гр.");
        allSettlementPlaceType.add( "с.");
        addressForm.setSettlementPlacesNomenclature(allSettlementPlaceType);

        Long settlementPlaceCode = setSettlementPlaceCode(foundAddress, municipalityCode);

        Long settlementPlaceCode2 = setSettlementPlaceCode(foundAddress2, municipalityCode2);

        if(settlementPlaceCode != null) {
            addressForm.setSettlementPlaceCode(settlementPlaceCode);
        }

        if(settlementPlaceCode2 != null ) {
            addressForm.setSettlementPlaceCode2(settlementPlaceCode2);
        }

        if (municipalityCode != null) {
            List<SettlementPlace> allSettlementPlacesForMunicipality = addressService.getAllSettlementPlacesByMunicipality(municipalityCode);
            addressForm.setSettlementPlaces(allSettlementPlacesForMunicipality);
        }

        if (municipalityCode2 != null) {
            List<SettlementPlace> allSettlementPlacesForMunicipality2 = addressService.getAllSettlementPlacesByMunicipality(municipalityCode2);
            addressForm.setSettlementPlaces2(allSettlementPlacesForMunicipality2);
        }
        System.out.println("!!!!!!!!!!!!! municipalityCode2 " + municipalityCode2);
        System.out.println("!!!!!!!!!!!!! municipalityCode " + municipalityCode);
        System.out.println("!!!!!!!!!!!!! regionCode2 " + regionCode2);
        System.out.println("!!!!!!!!!!!!! regionCode " + regionCode);
        System.out.println("IS ENABLED getIsMunicipalityEnable " + addressForm.getIsMunicipalityEnable());
        return addressForm;
    }

    private static Long setSettlementPlaceCode(Address foundAddress, String municipalityCode) {
        Long settlementPlaceCode = null;

        if (foundAddress != null && foundAddress.getSettlementPlace() != null && foundAddress.getSettlementPlace().getEkatte() != null
                && foundAddress.getSettlementPlace().getEkatte() != null) {
            if ( municipalityCode != null) {
                settlementPlaceCode = foundAddress.getSettlementPlace().getEkatte();
            }
        }
        return settlementPlaceCode;
    }

    private static Address setAddress(Long addressId, AddressService addressService, AddressForm addressForm) {

        Address foundAddress = addressService.findAddressWithSettlementPlaceByAddressId(addressId);

        if (foundAddress == null) {
            addressForm.setAddress(new Address());
        } else {
            addressForm.setAddress(foundAddress);
        }

        if (addressForm.getAddress().getSettlementPlace() == null) {
            addressForm.getAddress().setSettlementPlace(new SettlementPlace());
        }
        return foundAddress;
    }

    private static Address setAddress2(Long addressId2, AddressService addressService, AddressForm addressForm) {
        Address foundAddress2 = addressService.findAddressWithSettlementPlaceByAddressId(addressId2);

        if (foundAddress2 == null) {
            addressForm.setAddress2(new Address());
        } else {
            addressForm.setAddress2(foundAddress2);
        }

        if (addressForm.getAddress2().getSettlementPlace() == null) {
            addressForm.getAddress2().setSettlementPlace(new SettlementPlace());
        }
        return foundAddress2;
    }

    private static String setMunicipalityCode(AddressForm addressForm, HttpSession httpSession, Address foundAddress) {
        String municipalityCode = null;
        if (httpSession.getAttribute("municipalityCode")!= null) {
            municipalityCode = (String) httpSession.getAttribute("municipalityCode");
        }else if (foundAddress != null && foundAddress.getSettlementPlace() != null
                && foundAddress.getSettlementPlace().getMunicipality() != null
                && foundAddress.getSettlementPlace().getMunicipality().getMunicipalityCode() != null) {
            municipalityCode = foundAddress.getSettlementPlace().getMunicipality().getMunicipalityCode();

        }

        if(municipalityCode != null) {
            addressForm.setMunicipalityCode(municipalityCode);
            addressForm.setIsSettlementPlaceEnable(true);
        }
        return municipalityCode;
    }

    private static String setMunicipalityCode2(AddressForm addressForm, HttpSession httpSession, Address foundAddress) {
        String municipalityCode2 = null;
        if(httpSession.getAttribute("municipalityCode2")!= null) {
            System.out.println("!!   if(httpSession.getAttribute(\"municipalityCode2\")!= null");
            municipalityCode2 = (String) httpSession.getAttribute("municipalityCode2");
        } else if (foundAddress != null && foundAddress.getSettlementPlace() != null
                && foundAddress.getSettlementPlace().getMunicipality() != null
                && foundAddress.getSettlementPlace().getMunicipality().getMunicipalityCode() != null) {
            municipalityCode2 = foundAddress.getSettlementPlace().getMunicipality().getMunicipalityCode();
            System.out.println("!!!! municipalityCode2 = foundAddress.getSettlementPlace().getMunicipality().getMunicipalityCode();");

        }
System.out.println("isSettlementPlaceEnable2 >>>> " + municipalityCode2);
        if(municipalityCode2 != null) {
            addressForm.setMunicipalityCode2(municipalityCode2);
            addressForm.setIsSettlementPlaceEnable2(true);
        }
        return municipalityCode2;
    }

    private static String setRegionCode(AddressForm addressForm, HttpSession httpSession, Address foundAddress) {
        String regionCode = null;
        if (httpSession.getAttribute("regionCode") != null) {
            regionCode = (String) httpSession.getAttribute("regionCode");
        } else if (foundAddress != null && foundAddress.getSettlementPlace() != null
                && foundAddress.getSettlementPlace().getRegion() != null
                && foundAddress.getSettlementPlace().getRegion().getRegionCode() != null) {
            regionCode = foundAddress.getSettlementPlace().getRegion().getRegionCode();
        }

        if(regionCode != null) {
            addressForm.setRegionCode(regionCode);
            addressForm.setIsMunicipalityEnable(true);
            System.out.println("addressForm.setIsMunicipalityEnable(true);");
        }
        return regionCode;
    }

    private static String setRegionCode2(AddressForm addressForm, HttpSession httpSession, Address foundAddress) {
        String regionCode2 = null;
        if (httpSession.getAttribute("regionCode2") != null) {
            regionCode2 = (String) httpSession.getAttribute("regionCode2");
        } else if (foundAddress != null && foundAddress.getSettlementPlace() != null
                && foundAddress.getSettlementPlace().getRegion() != null
                && foundAddress.getSettlementPlace().getRegion().getRegionCode() != null) {
            regionCode2 = foundAddress.getSettlementPlace().getRegion().getRegionCode();
        }

        if(regionCode2 != null) {
            addressForm.setRegionCode2(regionCode2);
            addressForm.setIsMunicipalityEnable2(true);
        }
        return regionCode2;
    }


    public static List<String> getClassesNomenclature() {
        List<String> classesNomenclature = Arrays.asList("I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII");
        return classesNomenclature;
    }

    public static List<String> getClassLettersNomenclature() {
        List<String> classLettersNomenclature = Arrays.asList("А", "Б", "В", "Г", "Д", "Е", "Ж", "З", "И", "Й", "К", "Л", "М", "Н", "О");
        return classLettersNomenclature;
    }
}
