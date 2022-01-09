import { Component, ElementRef, ViewChild } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { EndpointsService } from './services/apiService/Endpoints.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {

  constructor(private api: EndpointsService){}

  buttonStatus: boolean = false;

  formGroup = new FormGroup({
    'aName': new FormControl(null, [Validators.required]),
    'eName': new FormControl(null, [Validators.required]),
    'address': new FormControl(null, []),
    'id': new FormControl(null, []),
    'image': new FormControl(null, []),
  });

  // change player image
  selectedImage: any;
  changePlayerImage($event: any) {
    this.selectedImage = $event.target.files[0];
  }

  saveSupermarket() {
    if(this.formGroup.valid && this.selectedImage) {
      this.api.saveSupermarket(this.formGroup.value, this.selectedImage).subscribe( (result) =>{
        this.getSupermarketList();
        this.resetFields();
        alert("element saved successfully ..")
      });
    } else {
      alert("please, insert all fields ..")
    }
  }

  deleteSupermarket(element: any) {
    let status = confirm("Are you sure you want to delete this item ?");
    if(status) {
      this.api.deleteSupermarket(element?.id).subscribe( (status: any) =>{
        if(status) {
          alert("supermarket deleted successfully ..");
          this.getSupermarketList();
        }
      });
    }
  }

  @ViewChild('fileImage', {static: false})
  fileImage?: ElementRef;

  supermarketList: any;
  getSupermarketList() {
    this.api.getSupermarketList().subscribe( (result: any) => {
      if(result) {
        this.supermarketList = result;
      }
    });
  }

  forUpdate: any;
  getElementForUpdate(element: any) {
    this.formGroup.setValue(element);
    this.forUpdate = element;
    this.buttonStatus = true;
  }

  updateSupermarket() {
    if(this.formGroup.valid && this.forUpdate) {
      this.formGroup.get('id')?.setValue(this.forUpdate.id);
      this.api.updateSupermarket(this.formGroup.value, this.selectedImage).subscribe( (result) =>{
        this.getSupermarketList();
        this.resetFields();
        alert("element updated successfully ..")
      });
    } else {
      alert("please, insert all fields ..")
    }
  }

  resetFields() {
    this.formGroup.reset();
    this.buttonStatus = false;
    this.fileImage!.nativeElement.value = '';
    this.selectedImage = '';
  }

  ngOnInit(): void {
    this.getSupermarketList();
  }
}
