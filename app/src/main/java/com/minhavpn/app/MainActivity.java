package com.minhavpn.app;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.net.Uri;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity extends Activity {
  private final int bg = Color.rgb(9,14,26), panel = Color.rgb(21,29,47);
  private final int white = Color.rgb(239,245,255), muted = Color.rgb(149,164,188);
  private final int accent = Color.rgb(67,220,187);
  private LinearLayout root;
  private TextView status, subtitle;
  private int dp(float n) { return (int)(n*getResources().getDisplayMetrics().density+0.5f); }
  private GradientDrawable shape(int color,int radius) {
    GradientDrawable d=new GradientDrawable(); d.setColor(color); d.setCornerRadius(dp(radius)); return d;
  }
  private TextView text(String s,int size,int color,boolean bold) {
    TextView t=new TextView(this); t.setText(s); t.setTextSize(size); t.setTextColor(color);
    if(bold)t.setTypeface(Typeface.DEFAULT,Typeface.BOLD); return t;
  }
  private void space(int height) { View v=new View(this); root.addView(v,new LinearLayout.LayoutParams(1,dp(height))); }
  private void addCard(LinearLayout parent, View content) {
    LinearLayout box=new LinearLayout(this); box.setOrientation(LinearLayout.VERTICAL); box.setPadding(dp(22),dp(22),dp(22),dp(22));
    box.setBackground(shape(panel,22)); box.addView(content); parent.addView(box,new LinearLayout.LayoutParams(-1,-2));
  }
  private Button button(String label,boolean primary) {
    Button b=new Button(this); b.setText(label); b.setTextSize(15); b.setAllCaps(false);
    b.setTypeface(Typeface.DEFAULT,Typeface.BOLD);
    b.setTextColor(primary?bg:white); b.setBackground(shape(primary?accent:Color.rgb(38,49,70),17));
    b.setPadding(dp(12),dp(12),dp(12),dp(12));
    LinearLayout.LayoutParams p=new LinearLayout.LayoutParams(-1,dp(58)); p.topMargin=dp(12); root.addView(b,p);
    return b;
  }
  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    getWindow().setStatusBarColor(bg); getWindow().setNavigationBarColor(bg);
    ScrollView scroll=new ScrollView(this); scroll.setFillViewport(true); scroll.setBackgroundColor(bg);
    root=new LinearLayout(this); root.setOrientation(LinearLayout.VERTICAL); root.setPadding(dp(24),dp(32),dp(24),dp(28));
    scroll.addView(root); setContentView(scroll);
    TextView brand=text("◈  MINHAVPN",14,accent,true); brand.setLetterSpacing(0.18f); root.addView(brand);
    space(34);
    TextView heading=text("Sua conexão.\nSeu controle.",36,white,true); heading.setLineSpacing(dp(2),1f); root.addView(heading);
    space(12);
    subtitle=text("Um espaço simples para acessar sua VPN com segurança.",15,muted,false); root.addView(subtitle);
    space(32);
    LinearLayout card=new LinearLayout(this); card.setOrientation(LinearLayout.VERTICAL);
    TextView tag=text("●  STATUS DA CONEXÃO",12,accent,true); tag.setLetterSpacing(0.1f); card.addView(tag);
    TextView main=text("Pronto para configurar",22,white,true);
    LinearLayout.LayoutParams mp=new LinearLayout.LayoutParams(-1,-2); mp.topMargin=dp(14); card.addView(main,mp);
    status=text("Instale o WireGuard e importe seu perfil para ativar o túnel VPN.",14,muted,false);
    LinearLayout.LayoutParams sp=new LinearLayout.LayoutParams(-1,-2); sp.topMargin=dp(10); card.addView(status,sp);
    addCard(root,card);
    space(18);
    Button open=button("↗   Abrir WireGuard",true);
    open.setOnClickListener(v->{
      Intent launch=getPackageManager().getLaunchIntentForPackage("com.wireguard.android");
      if(launch!=null)startActivity(launch);
      else startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("https://play.google.com/store/apps/details?id=com.wireguard.android")));
    });
    Button guide=button("☰   Como configurar minha VPN",false);
    guide.setOnClickListener(v->{
      status.setText("1. Configure um servidor WireGuard.\n\n2. Gere o perfil do celular (.conf ou QR).\n\n3. Abra o WireGuard oficial e importe o perfil.\n\n4. Ative o túnel no WireGuard.");
      main.setText("Guia de configuração");
    });
    space(34);
    TextView foot=text("PROTEÇÃO COMEÇA COM UMA BOA CONFIGURAÇÃO",11,muted,true);
    foot.setGravity(Gravity.CENTER); foot.setLetterSpacing(0.07f); root.addView(foot);
    space(10);
    TextView note=text("Este aplicativo é um painel auxiliar. A conexão VPN é controlada pelo WireGuard oficial.",12,muted,false);
    note.setGravity(Gravity.CENTER); root.addView(note);
  }
}
