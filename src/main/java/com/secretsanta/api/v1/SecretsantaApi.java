/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (5.3.0).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package com.secretsanta.api.v1;

import com.secretsanta.model.v1.Teammate;
import com.secretsanta.model.v1.Teammates;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2021-12-02T23:23:27.237Z[Europe/London]")
@Validated
@Api(value = "secretsanta", description = "the secretsanta API")
public interface SecretsantaApi {

    /**
     * GET /secretsanta/getAllSecretSanta/{year} : get all secret santas in a given year, assuming that secretsantas have not been selected for that year
     *
     * @param year the year of everyone&#39;s secret santa (required)
     * @return teammates who were selected as secretsantas for a year (status code 200)
     *         or unexpected error (status code 200)
     */
    @ApiOperation(value = "get all secret santas in a given year, assuming that secretsantas have not been selected for that year", nickname = "getAllSecretSanta", notes = "", response = Teammates.class, tags={ "teammate", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "teammates who were selected as secretsantas for a year", response = Teammates.class),
        @ApiResponse(code = 200, message = "unexpected error") })
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/secretsanta/getAllSecretSanta/{year}",
        produces = { "application/json" }
    )
    ResponseEntity<Teammates> getAllSecretSanta(@ApiParam(value = "the year of everyone's secret santa", required = true) @PathVariable("year") Integer year);


    /**
     * POST /secretsanta/registerTeammate : Create new teammate and persist data to secretsanta table in postgres
     *
     * @param teammate information of the teammate to create (required)
     * @return teammates who were selected as secretsantas for a year (status code 200)
     *         or unexpected error (status code 200)
     */
    @ApiOperation(value = "Create new teammate and persist data to secretsanta table in postgres", nickname = "registerTeammate", notes = "", response = Teammate.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "teammates who were selected as secretsantas for a year", response = Teammate.class),
        @ApiResponse(code = 200, message = "unexpected error") })
    @RequestMapping(
        method = RequestMethod.POST,
        value = "/secretsanta/registerTeammate",
        produces = { "application/json" },
        consumes = { "application/json" }
    )
    ResponseEntity<Teammate> registerTeammate(@ApiParam(value = "information of the teammate to create", required = true) @Valid @RequestBody Teammate teammate);

}
