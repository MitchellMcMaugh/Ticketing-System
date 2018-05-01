<?php
/**
 * The template for displaying pages
 *
 * This is the template that displays all pages by default.
 * Please note that this is the WordPress construct of pages and that
 * other "pages" on your WordPress site will use a different template.
 *
 * @package WordPress
 * @subpackage HBA_Intranet_Theme
 * @since HBA Intranet Theme 1.0
 */

get_header(); ?>

<div class="main-content">

<?php
if(isset($_GET['author_name'])) :
$curauth = get_userdatabylogin($author_name);
else :
$curauth = get_userdata(intval($author));
endif;
?>

	<div class="bread"><a href="<?php echo esc_url( home_url( '/' ) ); ?>">Home</a>&nbsp;&nbsp;/&nbsp;&nbsp;<a href="<?php echo esc_url( home_url( '/' ) ); ?>people/">People</a>&nbsp;&nbsp;/&nbsp;&nbsp;<?php echo $curauth->nickname; ?></div>

	<div class="lhs-menu">
		<?php if ( has_nav_menu( 'hba-way-menu' ) || has_nav_menu( 'social' ) ) : ?>
			<div class="menu">
				<?php if ( has_nav_menu( 'hba-way-menu' ) ) : ?>
					<?php
						wp_nav_menu( array(
							'theme_location' => 'hba-way-menu',
							'menu_class'     => 'hba-way-menu',
						 ) );
					?>
				<?php endif; ?>
			</div>
		<?php endif; ?>
	</div>

	<div class="center-content">

		
		<div class="content-head">
			<h2><?php echo $curauth->nickname; ?></h2>
			<h3><?php echo $curauth->position; ?></h3>
			<p><?php echo $curauth->division; ?></p>
			<p>Location: <?php echo $curauth->city; ?></p>
			<p>T: <?php echo $curauth->phone; ?> M: <?php echo $curauth->mobile; ?></p>
			<p>E: <a href="mailto:<?php echo $curauth->user_email; ?>"><?php echo $curauth->user_email; ?></a></p>
			<div class="av"><?php echo get_avatar($curauth->ID, 300, get_bloginfo('template_url').'/images/no-avatar.png'); ?></div>
			<!-- here -->
			<?php

			$author_id = get_the_author_meta('ID');
			$author_badge = get_field('pm_profile_bio', 'user_'. $author_id );

			?>
			<div class="content-middle">
			<div class="ww">
			<?php the_field('pm_profile_bio'); ?>
			<?php echo $author_badge; ?>
			</div>
			</div>

		</div>

		<!-- Begin posts -->
<?php /* ?>
		<div class="content-middle">
			<div class="ww">

			<?php while(has_sub_field("flexible_content")): ?>

				<?php if(get_row_layout() == "flexible_content"): // layout: Content ?>
			 
					<?php the_sub_field("content"); ?>
					
				<?php elseif(get_row_layout() == "subheading"): // layout: File ?>
			 
					<h3><?php the_sub_field('text'); ?></h3>

				<?php elseif(get_row_layout() == "hero_image"): // layout: File ?>

					<div class="ww-hero-image">
						<img src="<?php the_sub_field('image_hero'); ?>" />
					</div>

				<?php elseif(get_row_layout() == "embed"): // layout: File ?>

					<div class="ww-source-code"><?php the_sub_field('embed_code'); ?></div>
		
				<?php endif; ?>
			
			<?php endwhile; ?>

			</div>
		</div>
<?php */ ?>
		<!-- End posts -->

	</div>

	<div class="rhs-content">
		
	</div>


</div>



<?php get_footer(); ?>







