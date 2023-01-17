package aralves.com.family.api.controller;

import aralves.com.family.domain.model.Family;
import aralves.com.family.domain.services.CalculatePoints;
import aralves.com.family.domain.services.FamilyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/families")
@Api(tags = "Families", value = "families")
public class FamilyRestController {

    private final FamilyService familyService;

    private final CalculatePoints calculatePoints;

    @GetMapping
    @ApiOperation(value = "Get all families saved on database")
    public ResponseEntity<List<Family>> getFamilies() {
        return ResponseEntity.ok(familyService.getAllFamily());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Add a new family")
    public ResponseEntity<Family> addNewFamily(@RequestBody Family family) {
        return ResponseEntity.ok(familyService.addNewFamily(family));
    }

    @PostMapping("/calculate")
    @ApiOperation(value = "Calculate the points for each family")
    public ResponseEntity<List<Family>> calculate(){
        return ResponseEntity.ok(calculatePoints.calculateFamilies());
    }

}
