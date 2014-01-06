package net.minecraft.src;

public class ModelNumse extends ModelBase
{
  //fields
    ModelRenderer rightButt;
    ModelRenderer rightAssCheek;
    ModelRenderer leftButt;
    ModelRenderer leftAssCheek;
    ModelRenderer body;
    ModelRenderer bodyLeftSide;
    ModelRenderer bodyRightSide;
    ModelRenderer bodyUpper;
    ModelRenderer face;
    ModelRenderer Shape6;
    ModelRenderer Shape7;
    ModelRenderer Shape8;
    ModelRenderer Shape9;
    ModelRenderer leftHand;
    ModelRenderer leftHandPixel;
    ModelRenderer rightHand;
    ModelRenderer rightHandPixel;
    ModelRenderer leftFeet;
    ModelRenderer rightFeet;
    ModelRenderer leftFeetPixel;
    ModelRenderer rightFeetPixel;
  
  public ModelNumse()
  {
    textureWidth = 64;
    textureHeight = 32;
    
      rightButt = new ModelRenderer(this, 38, 6);
      rightButt.addBox(0F, 8F, 0F, 6, 6, 6);
      rightButt.setRotationPoint(0F, 4F, 0F);
      rightButt.setTextureSize(64, 32);
      rightButt.mirror = true;
      setRotation(rightButt, 0F, 0F, 0F);
      rightAssCheek = new ModelRenderer(this, 0, 0);
      rightAssCheek.addBox(0F, 8F, 0F, 4, 4, 1);
      rightAssCheek.setRotationPoint(1F, 5F, 6F);
      rightAssCheek.setTextureSize(64, 32);
      rightAssCheek.mirror = true;
      setRotation(rightAssCheek, 0F, 0F, 0F);
      leftButt = new ModelRenderer(this, 38, 6);
      leftButt.addBox(0F, 8F, 0F, 6, 6, 6);
      leftButt.setRotationPoint(-7F, 4F, 0F);
      leftButt.setTextureSize(64, 32);
      leftButt.mirror = true;
      setRotation(leftButt, 0F, 0F, 0F);
      leftAssCheek = new ModelRenderer(this, 0, 0);
      leftAssCheek.addBox(0F, 8F, 0F, 4, 4, 1);
      leftAssCheek.setRotationPoint(-6F, 5F, 6F);
      leftAssCheek.setTextureSize(64, 32);
      leftAssCheek.mirror = true;
      setRotation(leftAssCheek, 0F, 0F, 0F);
      body = new ModelRenderer(this, 0, 12);
      body.addBox(0F, 8F, 0F, 7, 5, 11);
      body.setRotationPoint(-4F, 6F, -6F);
      body.setTextureSize(64, 32);
      body.mirror = true;
      setRotation(body, 0F, 0F, 0F);
      bodyLeftSide = new ModelRenderer(this, 11, 20);
      bodyLeftSide.addBox(0F, 0F, 0F, 1, 3, 5);
      bodyLeftSide.setRotationPoint(-5F, 15F, -5F);
      bodyLeftSide.setTextureSize(64, 32);
      bodyLeftSide.mirror = true;
      setRotation(bodyLeftSide, 0F, 0F, 0F);
      bodyRightSide = new ModelRenderer(this, 11, 20);
      bodyRightSide.addBox(0F, 0F, 0F, 1, 3, 5);
      bodyRightSide.setRotationPoint(3F, 15F, -5F);
      bodyRightSide.setTextureSize(64, 32);
      bodyRightSide.mirror = true;
      setRotation(bodyRightSide, 0F, 0F, 0F);
      bodyUpper = new ModelRenderer(this, 10, 18);
      bodyUpper.addBox(-3F, 13F, -5F, 5, 1, 9);
      bodyUpper.setRotationPoint(0F, 0F, -1F);
      bodyUpper.setTextureSize(64, 32);
      bodyUpper.mirror = true;
      setRotation(bodyUpper, 0F, 0F, 0F);
      face = new ModelRenderer(this, 12, 0);
      face.addBox(0F, 8F, 0F, 5, 4, 1);
      face.setRotationPoint(-3F, 6F, -7F);
      face.setTextureSize(64, 32);
      face.mirror = true;
      setRotation(face, 0F, 0F, 0F);
      Shape6 = new ModelRenderer(this, 25, 0);
      Shape6.addBox(0F, 8F, 0F, 4, 1, 4);
      Shape6.setRotationPoint(-6F, 3F, 1F);
      Shape6.setTextureSize(64, 32);
      Shape6.mirror = true;
      setRotation(Shape6, 0F, 0F, 0F);
      Shape7 = new ModelRenderer(this, 25, 0);
      Shape7.addBox(0F, 0F, 0F, 4, 1, 4);
      Shape7.setRotationPoint(1F, 11F, 1F);
      Shape7.setTextureSize(64, 32);
      Shape7.mirror = true;
      setRotation(Shape6, 0F, 0F, 0F);
      Shape8 = new ModelRenderer(this, 45, 10);
      Shape8.addBox(0F, 0F, 0F, 1, 4, 4);
      Shape8.setRotationPoint(-8F, 13F, 1F);
      Shape8.setTextureSize(64, 32);
      Shape8.mirror = true;
      setRotation(Shape8, 0F, 0F, 0F);
      Shape9 = new ModelRenderer(this, 45, 10);
      Shape9.addBox(0F, 0F, 0F, 1, 4, 4);
      Shape9.setRotationPoint(6F, 13F, 1F);
      Shape9.setTextureSize(64, 32);
      Shape9.mirror = true;
      setRotation(Shape9, 0F, 0F, 0F);
      leftHand = new ModelRenderer(this, 0, 6);
      leftHand.addBox(0F, 0F, 0F, 1, 1, 3);
      leftHand.setRotationPoint(-6F, 16F, -7F);
      leftHand.setTextureSize(64, 32);
      leftHand.mirror = true;
      setRotation(leftHand, 0F, 0F, 0F);
      leftHandPixel = new ModelRenderer(this, 9, 8);
      leftHandPixel.addBox(0F, 0F, 0F, 1, 1, 1);
      leftHandPixel.setRotationPoint(-6F, 17F, -8F);
      leftHandPixel.setTextureSize(64, 32);
      leftHandPixel.mirror = true;
      setRotation(leftHandPixel, 0F, 0F, 0F);
      rightHand = new ModelRenderer(this, 0, 6);
      rightHand.addBox(0F, 0F, 0F, 1, 1, 3);
      rightHand.setRotationPoint(4F, 16F, -7F);
      rightHand.setTextureSize(64, 32);
      rightHand.mirror = true;
      setRotation(rightHand, 0F, 0F, 0F);
      rightHandPixel = new ModelRenderer(this, 9, 8);
      rightHandPixel.addBox(0F, 0F, 0F, 1, 1, 1);
      rightHandPixel.setRotationPoint(4F, 17F, -8F);
      rightHandPixel.setTextureSize(64, 32);
      rightHandPixel.mirror = true;
      setRotation(rightHandPixel, 0F, 0F, 0F);
      leftFeet = new ModelRenderer(this, 9, 7);
      leftFeet.addBox(0F, 0F, 0F, 1, 2, 1);
      leftFeet.setRotationPoint(3F, 9F, 4F);
      leftFeet.setTextureSize(64, 32);
      leftFeet.mirror = true;
      setRotation(leftFeet, 0F, 0F, 0F);
      rightFeet = new ModelRenderer(this, 9, 7);
      rightFeet.addBox(0F, 0F, 0F, 1, 2, 1);
      rightFeet.setRotationPoint(-5F, 9F, 4F);
      rightFeet.setTextureSize(64, 32);
      rightFeet.mirror = true;
      setRotation(rightFeet, 0F, 0F, 0F);
      leftFeetPixel = new ModelRenderer(this, 9, 8);
      leftFeetPixel.addBox(0F, 0F, 0F, 1, 1, 1);
      leftFeetPixel.setRotationPoint(3F, 8F, 3F);
      leftFeetPixel.setTextureSize(64, 32);
      leftFeetPixel.mirror = true;
      setRotation(leftFeetPixel, 0F, 0F, 0F);
      rightFeetPixel = new ModelRenderer(this, 9, 8);
      rightFeetPixel.addBox(0F, 0F, 0F, 1, 1, 1);
      rightFeetPixel.setRotationPoint(-5F, 8F, 3F);
      rightFeetPixel.setTextureSize(64, 32);
      rightFeetPixel.mirror = true;
      setRotation(rightFeetPixel, 0F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    rightButt.render(f5);
    rightAssCheek.render(f5);
    leftButt.render(f5);
    leftAssCheek.render(f5);
    body.render(f5);
    bodyLeftSide.render(f5);
    bodyRightSide.render(f5);
    bodyUpper.render(f5);
    face.render(f5);
    Shape6.render(f5);
    Shape7.render(f5);
    Shape8.render(f5);
    Shape9.render(f5);
    leftHand.render(f5);
    leftHandPixel.render(f5);
    rightHand.render(f5);
    rightHandPixel.render(f5);
    leftFeet.render(f5);
    rightFeet.render(f5);
    leftFeetPixel.render(f5);
    rightFeetPixel.render(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
  {
    super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
  }

}
