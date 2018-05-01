<?php
/**
 * The template for displaying the footer
 *
 * Contains the closing of the #content div and all content after
 *
 * @package WordPress
 * @subpackage HBA_Intranet_Theme
 * @since HBA Intranet Theme 1.0
 */
?>

</div><!-- .site-content -->
<?php wp_footer(); ?>

<script src="<?php echo site_url() ?>/wp-content/themes/hbaintranettheme/js/jquery.hoverintent.js"></script>
<script src="<?php echo site_url() ?>/wp-content/themes/hbaintranettheme/js/jquery.cycle2.min.js"></script>
<script src="<?php echo site_url() ?>/wp-content/themes/hbaintranettheme/js/jquery.cycle2.swipe.min.js"></script>
<script src="<?php echo site_url() ?>/wp-content/themes/hbaintranettheme/js/jquery.swipebox.min.js"></script>
<script src="<?php echo site_url() ?>/wp-content/themes/hbaintranettheme/js/hba-main.js?140318"></script>

<script>
jQuery(function() {
        jQuery('a[href$=".pdf"]').prop('target', '_blank');
});
</script>
</body>
</html>
