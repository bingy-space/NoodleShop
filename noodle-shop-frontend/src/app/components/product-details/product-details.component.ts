import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CartItem } from 'src/app/common/cart-item';
import { Product } from 'src/app/common/product';
import { CartService } from 'src/app/services/cart.service';
import { ProductService } from 'src/app/services/product.service';

@Component({
  selector: 'app-product-details',
  templateUrl: './product-details.component.html',
  styleUrls: ['./product-details.component.css']
})
export class ProductDetailsComponent implements OnInit {

  // The ! is the not-null assertion operator. Tell TypeScript compiler to suspend strict null and undefined checks for a property
  product!: Product;

  constructor(private productService: ProductService, private route: ActivatedRoute, private cartService: CartService) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe(() =>{
      this.handleProductDetails();
    })
  }

  handleProductDetails() {
    // Get the 'id' param string. Convert string to a number using the "+" symbol
    const theProductId: number = +this.route.snapshot.paramMap.get('id')!;

    this.productService.getProductDetail(theProductId).subscribe(
      data =>{
        this.product = data;
      }
    )
  }

  addToCart(){
    const theCartItem = new CartItem(this.product);
    this.cartService.addToCart(theCartItem);
  }

}
