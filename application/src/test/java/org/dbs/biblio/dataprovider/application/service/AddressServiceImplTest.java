package org.dbs.biblio.dataprovider.application.service;

import org.dbs.biblio.dataprovider.application.port.in.cmd.ReferenceAddress;
import org.dbs.biblio.dataprovider.domain.address.Address;
import org.dbs.biblio.dataprovider.domain.address.Street;
import org.dbs.biblio.dataprovider.exception.BusinessObjectAllReadyExist;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AddressServiceImplTest {

    public static final String TEST_STREET_NAME = "Allée jean de la Bruyère";
    public static final String TYPE_POSITION = "logement";
    public static final String CITY_TEST = "Versailles";
    public static final int TEST_NUMBER = 1;
    public static final float POS_X = 1.1F;
    public static final float POS_Y = 1.1F;
    public static final float POS_LON = 2.2F;
    public static final float POS_LAT = 2.2F;
    public static final String CODE_POSTAL_TEST = "78000";
    public static final String CODE_INSEE_TEST = "78000";

    public AddressServiceImplTest() {
        this.addressTownRepositoryStub = new AddressTownRepositoryStub();
        this.addressService = new AddressServiceImpl(this.addressTownRepositoryStub, this.addressTownRepositoryStub);
    }

    private final AddressTownRepositoryStub addressTownRepositoryStub;
    private final AddressServiceImpl addressService;
    private boolean addressAllReadyExist = false;

    @BeforeEach
    void setUp() {
        this.addressTownRepositoryStub.clear();
        this.addressAllReadyExist = false;
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("Simple persist Test")
    void referenceAddress() {
        // Given the systems doesn't know the Address

        // When new cmd is received
        ReferenceAddress.ReferenceAddressCmd referenceAddressCmd = new ReferenceAddress.ReferenceAddressCmd(1, null, "Allée jean de la Bruyère",
                new ReferenceAddress.CreatePositionCmd(1.1F, 1.1F, 2.2F, 2.2F, "logement"),
                new ReferenceAddress.CreateCityCmd("78000", "78000", "Versailles"));

        try {
            addressService.referenceAddress(referenceAddressCmd);
        } catch (BusinessObjectAllReadyExist businessObjectAllReadyExist) {
            addressAllReadyExist = true;
        }

        // Then the address is know and the city to
        assertThat(addressAllReadyExist).isFalse();
        assertThat(this.addressTownRepositoryStub.haveAddress("Versailles",
                new Street.StreetBuilder().setStreetName("Allée jean de la Bruyère").build(),
                1,
                null))
                .as("Check if this the correct address").isTrue();
        assertThat(this.addressTownRepositoryStub.haveTown("78000", "78000", "Versailles"))
                .as("Check if the commune is %s", "Versailles").isTrue();
    }

    @Test
    @DisplayName("Could not persist a all ready knows address")
    void referenceExistAddress() {

        // Given the systems know the Address
        Address address = new Address.AddressBuilder()
                .setNomCommune(CITY_TEST)
                .setNumero(TEST_NUMBER)
                .setRep(TYPE_POSITION)
                .setStreet(new Street.StreetBuilder()
                        .setStreetName(TEST_STREET_NAME)
                        .build())
                .build();
        this.addressTownRepositoryStub.reference(address);

        // When new cmd is received
        ReferenceAddress.ReferenceAddressCmd referenceAddressCmd = new ReferenceAddress.ReferenceAddressCmd(TEST_NUMBER, null, TEST_STREET_NAME,
                new ReferenceAddress.CreatePositionCmd(POS_X, POS_Y, POS_LON, POS_LAT, TYPE_POSITION),
                new ReferenceAddress.CreateCityCmd(CODE_POSTAL_TEST, CODE_INSEE_TEST, CITY_TEST));

        try {
            addressService.referenceAddress(referenceAddressCmd);
        } catch (BusinessObjectAllReadyExist businessObjectAllReadyExist) {
            addressAllReadyExist = true;
        }

        // Then the address is know and the city to
        assertThat(addressAllReadyExist).isTrue();
    }
}