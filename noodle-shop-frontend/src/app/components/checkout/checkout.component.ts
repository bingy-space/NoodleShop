import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Country } from 'src/app/common/country';
import { ShopFormService } from 'src/app/services/shop-form.service';

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.css']
})
export class CheckoutComponent implements OnInit {
  checkoutFormGroup: FormGroup;
  
  totalPrice: number = 0;
  totalQuantity: number = 0;

  creditCardYears: number[] = [];
  creditCardMonths: number[] = [];

  countries: Country[] = [];

  constructor(private formBuilder: FormBuilder, private shopFormService: ShopFormService) { }

  ngOnInit(): void {
    this.checkoutFormGroup = this.formBuilder.group({
      customer: this.formBuilder.group({
        firstName: [''],
        lastName: [''],
        email: ['']
      }),
      shippingAddress: this.formBuilder.group({
        street:[''],
        city:[''],
        state: [''],
        country: [''],
        zipCode: ['']
      }),
      billingAddress: this.formBuilder.group({
        street:[''],
        city:[''],
        state: [''],
        country: [''],
        zipCode: ['']
      }),
      creditCard: this.formBuilder.group({
        cardType:[''],
        nameOnCard:[''],
        cardNumber: [''],
        securityCode: [''],
        expirationMonth: [''],
        expirationYear:['']
      })
    });

    // Populate Credit Card Months
    const startMonth: number = new Date().getMonth() + 1;

    this.shopFormService.getCreditCardMonths(startMonth).subscribe(data => {
      this.creditCardMonths = data;
    })

    // Populate Credit Card Years
    this.shopFormService.getCreditCardYears().subscribe(data => {
      this.creditCardYears = data;
    })

    // Populate Countries
    this.shopFormService.getCountries().subscribe(
      data => {
        console.log("Retrieved countries: "+JSON.stringify(data));
        this.countries = data;
      }
    )
  }

  onSubmit(){
    console.log("Handling the submit button")
    console.log(this.checkoutFormGroup.get('customer').value);
  }

  copyShippingAddressToBillingAddress(event: Event){
    const isChecked = (<HTMLInputElement>event.target).checked
    if(isChecked){
      this.checkoutFormGroup.controls['billingAddress'].setValue(this.checkoutFormGroup.controls['shippingAddress'].value);
    }
    else{
      this.checkoutFormGroup.controls['billingAddress'].reset();
    }
  }

  handleMonthsAndYears(){
    const creditCardFormGroup = this.checkoutFormGroup.get('creditCard');

    const currentYear: number = new Date().getFullYear();
    const selectedYear: number = Number(creditCardFormGroup.value.expirationYear);

    // If the current year equals the selected year, then start with the current month
    let startMonth: number;

    if(currentYear ===  selectedYear){
      // Get the current month
      startMonth = new Date().getMonth() + 1;
    }else{
      startMonth = 1;
    }

    // Get the credit card month
    this.shopFormService.getCreditCardMonths(startMonth).subscribe(data => {
      this.creditCardMonths = data;
    })
  }

}
