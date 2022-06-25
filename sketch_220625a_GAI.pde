star [] stars = new star[200];
float y=324;
//�������
void setup() {
  size(700, 800);
  //��ʼ��200������
  for (int i=0; i<stars.length; i++) {
    stars[i]=new star();
  }
}

//�滭
void draw() {
  //���ñ���
  background(0);
  noStroke();
  //����Բ�β������ɫ�����Ͻ�
  fill(255, 255, 0);
  ellipse(40, 32, 251, 251);
  //����Բ�β������ɫ
  fill(255, 255, 0, 88);
  ellipse(40, 32, 263, 263);
  ellipse(40, 32, 280, 280);
  ellipse(40, 32, 294, 294);
  //����ֱ�߲�����ֱ�߿��,����
  stroke(234,171,212);
  strokeWeight(0.5);
  line(444,310,598,168);
  line(289,393,227,451);
  line(255,131,100,285);
  line(633,351,240,746);
  
  line(559,633,454,752);
  line(323,573,118,746);
  line(220,351,10,532);
  line(798,633,566,867);
  
  //ȡ��������ʽ������Բ�������ɫ
  noStroke();
  fill(61,120,133);
  ellipse(51,891,236,187);
  ellipse(157,882,263,229);
  ellipse(558,891,353,218);
  ellipse(377,864,295,236);
  ellipse(51,891,236,187);
  //ÿ200֡С�������۱���
  if (frameCount%200==0) {
    run=true;
  }
  //��������
  for (int i=0; i<stars.length; i++) {
    stars[i].draw();
  }
  push();
  translate(399, 407);
  rotate(radians(-65));
  scale(0.21, 0.21);
  //����С���˻���
  yp(true);
  pop();
  cloud();
  
  //��������
  stroke(200);
  strokeWeight(3);
  line(121, 226, 121, 500);
  line(180, 226, 180, 500);
  for (int i=252; i<470; i+=36) {
    line(121, i, 180, i);
  }

  line(497, 504, 497, 756);
  line(564, 504, 564, 756);
  for (int i=538; i<751; i+=36) {
    line(497, i, 564, i);
  }
  push();
  translate(65, y);
  scale(0.21, 0.21);
  //�����¥��С����
  yp(false);
  pop();
}

//���̼����¼�
void keyPressed() {
  if (keyPressed&&key=='w'||key=='W') {
    if (y>108) {
      y-=36;
    }
  }
  if (keyPressed&&key=='s'||key=='S') {
    if (y<324) {
      y+=36;
    }
  }
}

// ����
class star {
  float x, y;
  float size;
  color c;
  float sped;
  star() {
    //����λ�����,�ߴ��С���,��˸Ƶ�����
    x=random(width);
    y=random(height);
    size=random(0.2, 1);
    sped=random(0.001, 0.005);
    c=color(random(200, 255), random(200, 255), 30);
  }
  //�������ǣ��任��С
  void draw() {
    noStroke();
    fill(c);
    push();
    translate(x, y);
    scale(size);
    //�����������״�����ֶ�����Ӻ������������ϵ�����ͼ��
    beginShape();
    vertex(0, -12);
    vertex(-3, -5);
    vertex(-10, -5);
    vertex(-5, 1);
    vertex(-10, 10);
    vertex(0, 5);
    vertex(10, 10);
    vertex(5, 1);
    vertex(10, -5);
    vertex(3, -5);
    endShape(CLOSE);

    pop();
    //ellipse(x, y, size, size);
    size+=sped;
    if (size>1.0||size<0.2) {
      sped*=-1;
    }
  }
}

//�����ƶ䣬����ɫ��������Բ���
void cloud() {
  noStroke();
  fill(240);
  ellipse(155, 188, 189, 82);
  ellipse(132, 173, 106, 86);
  ellipse(167, 176, 103, 79);
  ellipse(141, 206, 101, 87);
  ellipse(171, 202, 95, 82);

  ellipse(496, 3, 208, 100);
  ellipse(458, 23, 100, 85);
  ellipse(494, 27, 100, 100);
  ellipse(568, 20, 164, 100);
  ellipse(564, 41, 108, 100);

  ellipse(520, 435, 174, 100);
  ellipse(486, 448, 123, 100);
  ellipse(550, 417, 159, 94);
  ellipse(571, 442, 170, 100);
  ellipse(465, 420, 144, 108);

  ellipse(226, 654, 268, 135);
  ellipse(124, 633, 152, 119);
  ellipse(235, 614, 169, 100);
  ellipse(263, 680, 162, 100);
  ellipse(180, 676, 179, 100);

  ellipse(89, 844, 62, 52);
  ellipse(117, 836, 62, 50);
  ellipse(150, 840, 48, 33);
  ellipse(113, 848, 66, 37);

  ellipse(641, 830, 47, 45);
  ellipse(611, 824, 63, 48);
  ellipse(620, 833, 51, 47);
  ellipse(575, 827, 90, 48);
}
float h=93;
float hped=2;
boolean run;

//����С���ˣ�true��ʾ�����Ҳ�С���ˣ�false��ʾ���������¥��С����
void yp(boolean flag) {
  noFill();
  strokeWeight(2);
  stroke(200);
  beginShape();
  vertex(367, 242);
  bezierVertex(345, 222, 332, 228, 325, 231);
  endShape();
  beginShape();
  vertex(374, 247);
  bezierVertex(349, 233, 332, 233, 317, 244);
  endShape();

  beginShape();
  vertex(413, 245);
  bezierVertex(421, 229, 440, 224, 465, 228);
  endShape();

  beginShape();
  vertex(413, 245);
  bezierVertex(428, 229, 453, 236, 462, 238);
  endShape();
  strokeWeight(1);
  //shou
  fill(0);
  beginShape();
  vertex(527, 555);
  bezierVertex(511, 579, 545, 603, 562, 593);
  vertex(563, 591);
  bezierVertex(521, 562, 568, 582, 527, 555);
  endShape();

  beginShape();
  vertex(270, 550);
  bezierVertex(278, 579, 247, 591, 227, 586);
  vertex(227, 584);
  bezierVertex(265, 572, 230, 561, 270, 550);
  endShape();
  //�Ҳ�С��������
  noStroke();
  fill(246, 202, 69);
  beginShape();
  vertex(415, 243);
  bezierVertex(298, 228, 256, 314, 273, 434);
  bezierVertex(263, 544, 237, 625, 404, 638);
  bezierVertex(575, 623, 519, 536, 524, 436);
  bezierVertex(532, 337, 531, 257, 415, 242);
  endShape();
  //С�������۱���
  if (flag) {
    fill(0);
    quad(307, 327, 307, 349, 271, 360, 273, 341);
    quad(463, 324, 526, 346, 527, 370, 463, 346);

    noFill();
    stroke(253, 251, 238);
    strokeWeight(11);
    ellipse(385, 336, 147, 135);
    strokeWeight(1);
    noStroke();
    fill(255);
    ///eye
    ellipse(385, 353, 147, h);
    if (run) {
      h-=hped;
      if (h<0) {
        hped*=-1;
      }
      if (h>93) {
        h=93;
        hped*=-1;
        run=false;
      }
    }
    fill(100, 79, 47);
    //ellipse(382, 339, 63, 62);
    ellipse(382, 339, 63, h/1.5);
    //zui
    noFill();
    stroke(100);
    beginShape();
    vertex(342, 425);
    bezierVertex(378, 454, 423, 440, 442, 420);
    endShape();
  }
  fill(246, 202, 69);
  noStroke();
  ////���С���˸첲
  beginShape();
  vertex(524, 484);
  bezierVertex(571, 508, 617, 514, 545, 568);
  vertex(532, 558);
  bezierVertex(567, 531, 563, 511, 527, 518);
  endShape();
  ///���С������
  noStroke();
  fill(45, 50, 69);
  quad(339, 609, 390, 634, 394, 675, 343, 678);
  ellipse(330, 668, 25, 25);
  quad(333, 673, 356, 659, 346, 677, 332, 680);
  quad(419, 631, 469, 625, 469, 674, 419, 675);
  ellipse(482, 663, 27, 25);
  quad(464, 665, 481, 668, 485, 675, 464, 674);
  ///���С���˶���
  fill(66, 79, 113);
  beginShape();
  vertex(269, 560);
  bezierVertex(271, 615, 369, 640, 401, 637);
  bezierVertex(491, 638, 527, 595, 528, 563);
  vertex(528, 563);
  bezierVertex(466, 605, 483, 532, 484, 501);
  vertex(484, 501);
  bezierVertex(435, 505, 368, 500, 320, 500);
  vertex(320, 500);
  bezierVertex(322, 551, 325, 599, 269, 560);
  endShape();

}
